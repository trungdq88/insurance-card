package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.CardDao;
import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.dao.helper.NewCardRequestDao;
import com.fpt.mic.micweb.model.dto.ContractSearchResultDto;
import com.fpt.mic.micweb.model.entity.CardEntity;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.model.entity.NewCardRequestEntity;
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

    public CardEntity updateCardID(String contractCode, String cardID) {
        CardDao cardDao = new CardDao();
        ContractDao contractDao = new ContractDao();
        NewCardRequestDao newCardRequestDao = new NewCardRequestDao();

        // Validate contract code, card ID
        // Basic validate if the card is already in the system
        CardEntity checkCard = cardDao.read(cardID);
        if (checkCard != null) {
            return null; // TODO: return meaningful value here.
        }

        // New Card Request ID (if present)
        Integer requestId = null;

        // If there is a card assigned to the contract before, deactivate it
        CardEntity oldCard = cardDao.getCardByContract(contractCode);
        if (oldCard != null) {

            // If there is a new card request for the old card, resolve it.
            NewCardRequestEntity request = newCardRequestDao.getUnsolvedRequestByCardId(oldCard.getCardId());
            if (request != null) {
                // Resolve the request
                request.setResolveDate(new Timestamp(new Date().getTime()));
                newCardRequestDao.update(request);
                // Set request ID to the card
                requestId = request.getId();

                // TODO: Notify?
            }


            oldCard.setDeactivatedDate(new Timestamp((new Date()).getTime()));
            cardDao.update(oldCard);
        }

        // Insert new card to database
        CardEntity cardEntity = new CardEntity();
        cardEntity.setContractCode(contractCode);
        cardEntity.setCardId(cardID);
        cardEntity.setActivatedDate(new Timestamp(new Date().getTime()));
        cardEntity.setNewCardRequestId(requestId);

        CardEntity result = cardDao.create(cardEntity);

        if (result != null) {
            // Change contract status
            ContractEntity contract = contractDao.read(contractCode);
            if (contract.getStatus().equals(Constants.ContractStatus.NO_CARD)) {
                contract.setStatus(Constants.ContractStatus.READY);
            }
            contractDao.update(contract);
        }

        return result;
    }

    public CardEntity checkCard(String cardID) {
        CardDao cardDao = new CardDao();
        return cardDao.checkCard(cardID);

    }
}
