package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.framework.responses.JsonString;
import com.fpt.mic.micweb.model.dao.*;
import com.fpt.mic.micweb.model.dao.helper.NewCardRequestDao;
import com.fpt.mic.micweb.model.dto.CheckCardResponseDto;
import com.fpt.mic.micweb.model.dto.ContractSearchResultDto;
import com.fpt.mic.micweb.model.entity.*;
import com.fpt.mic.micweb.utils.Constants;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/6/15.
 */
public class ApiBusiness {
    /**
     * Search for contract
     * @param keyword could be contract_code or customer name
     * @return List {@link List<ContractEntity>}
     */
    public List<ContractSearchResultDto> searchContracts(String keyword) {
        ContractDao contractDao = new ContractDao();
        return contractDao.searchContracts(keyword);
    }

    public CardInstanceEntity updateCardID(String contractCode, String cardID) {
        CardInstanceDao cardInstanceDao = new CardInstanceDao();
        ContractDao contractDao = new ContractDao();
        NewCardRequestDao newCardRequestDao = new NewCardRequestDao();
        CardDao cardDao = new CardDao();

        // Check if contract is exists
        ContractEntity contract = contractDao.read(contractCode);

        if (contract == null) {
            // Contract not exists, nothing to do here
            return null;
        }

        // Validate contract code, card ID
        // Basic validate if the card is already in the system
        List cards = cardInstanceDao.getCardInstancesByCardID(cardID);
        if (cards.size() > 0) {
            // The card is already exists in the system,
            // check if the card status is AVAILABLE so we can recycle the old card
            CardEntity card = cardDao.read(cardID);
            if (card.getStatus() == CardEntity.STATUS_AVAILABLE) {
                // Recycle the card
                card.setStatus(CardEntity.STATUS_BUSY);
                cardDao.update(card);

                // Let the code continue to run.

            } else {
                // Sorry the card is being used.
                return null;
            }
        } else {
            // The card is not exists in the system, create new one.
            CardEntity cardEntity = new CardEntity();
            cardEntity.setCardId(cardID);
            cardEntity.setStatus(CardEntity.STATUS_BUSY);
            cardDao.create(cardEntity);
        }

        // New Card Request ID (if present)
        Integer requestId = null;

        // If there is a card assigned to the contract before, deactivate it
        CardInstanceEntity oldCard = cardInstanceDao.getActiveCardInstanceByContract(contractCode);
        if (oldCard != null) {
            // Deactivate the card instance
            oldCard.setDeactivatedDate(new Timestamp((new Date()).getTime()));
            cardInstanceDao.update(oldCard);
            // Set status for the card
            CardEntity cardEntity = cardDao.read(cardID);
            // cardEntity.setStatus(CardEntity.STATUS_AVAILABLE); // Uncomment this if accept auto-recycle cards
            cardDao.update(cardEntity);
        }

        // If there is a new card request for the old card, resolve it.
        NewCardRequestEntity request = newCardRequestDao.getUnresolveRequest(contractCode);
        if (request != null) {
            // Resolve the request
            request.setResolveDate(new Timestamp(new Date().getTime()));
            newCardRequestDao.update(request);
            // Set request ID to the card
            requestId = request.getId();

            // TODO: Notify?
        }

        // Insert new card to database
        CardInstanceEntity cardInstanceEntity = new CardInstanceEntity();
        cardInstanceEntity.setContractCode(contractCode);
        cardInstanceEntity.setCardId(cardID);
        cardInstanceEntity.setActivatedDate(new Timestamp(new Date().getTime()));
        cardInstanceEntity.setNewCardRequestId(requestId);
        cardInstanceEntity.setCustomerCode(contract.getCustomerCode());

        CardInstanceEntity result = cardInstanceDao.create(cardInstanceEntity);

        if (result != null) {
            // Change contract status
            if (contract.getStatus().equals(Constants.ContractStatus.NO_CARD)) {
                contract.setStatus(Constants.ContractStatus.READY);
            }
            contractDao.update(contract);
        }

        return result;
    }

    public CheckCardResponseDto checkCard(String deviceID, String cardID) {
        CardInstanceDao cardInstanceDao = new CardInstanceDao();
        CardAccessLogDao cardAccessLogDao = new CardAccessLogDao();
        CardBusiness cardBusiness = new CardBusiness();

        CardInstanceEntity card = cardInstanceDao.checkCard(cardID);
        CheckCardResponseDto result = null;

        if (card != null) {
            // Log down the access activity
            CardAccessLogEntity cardAccessLogEntity = new CardAccessLogEntity();
            cardAccessLogEntity.setAccessDate(new Timestamp(new Date().getTime()));
            cardAccessLogEntity.setCardInstanceId(card.getId());
            cardAccessLogEntity.setDevice(deviceID);
            cardAccessLogEntity.setRequestService(CardAccessLogEntity.SERVICE_CHECK_CARD);
            cardAccessLogEntity.setResponseContent(cardBusiness.getCardValidation(card));

            cardAccessLogDao.create(cardAccessLogEntity);


            result = new CheckCardResponseDto(card,
                    cardBusiness.getCardValidationCode(card));
        }

        // Return result
        return result;

    }

    /**
     * Update punishment information for contract
     * @param contractCode
     * @param title
     * @param photo
     * @return
     */
    public boolean updatePunishment(String deviceID, String contractCode, String title, String photo) {
        CardInstanceDao cardInstanceDao = new CardInstanceDao();
        CardAccessLogDao cardAccessLogDao = new CardAccessLogDao();
        ContractDao contractDao = new ContractDao();
        PunishmentDao punishmentDao = new PunishmentDao();

        ContractEntity contract = contractDao.read(contractCode);
        if (contract == null) {
            return false;
        }

        PunishmentEntity entity = new PunishmentEntity();
        entity.setContractCode(contractCode);
        entity.setTitle(title);
        entity.setAttachment(photo);
        entity.setLastModified(new Timestamp(new Date().getTime()));
        entity.setCreatedDate(new Timestamp(new Date().getTime()));
        PunishmentEntity result = punishmentDao.create(entity);

        CardInstanceEntity cardInstance = cardInstanceDao.getActiveCardInstanceByContract(contractCode);

        if (result != null) {
            // Log down the access activity
            CardAccessLogEntity cardAccessLogEntity = new CardAccessLogEntity();
            cardAccessLogEntity.setAccessDate(new Timestamp(new Date().getTime()));
            cardAccessLogEntity.setCardInstanceId(cardInstance.getId());
            cardAccessLogEntity.setDevice(deviceID);
            cardAccessLogEntity.setRequestService(CardAccessLogEntity.SERVICE_ADD_PUNISHMENT);
            cardAccessLogEntity.setResponseContent(result.getId() + "");

            cardAccessLogDao.create(cardAccessLogEntity);
        }


        return true;
    }
}
