package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.CardAccessLogDao;
import com.fpt.mic.micweb.model.dao.CardDao;
import com.fpt.mic.micweb.model.dao.CardInstanceDao;
import com.fpt.mic.micweb.model.dao.helper.NewCardRequestDao;
import com.fpt.mic.micweb.model.dto.NotificationBuilder;
import com.fpt.mic.micweb.model.dto.form.NewCardRequestDto;
import com.fpt.mic.micweb.model.dto.form.RecycleCardDto;
import com.fpt.mic.micweb.model.entity.CardAccessLogEntity;
import com.fpt.mic.micweb.model.entity.CardEntity;
import com.fpt.mic.micweb.model.entity.CardInstanceEntity;
import com.fpt.mic.micweb.model.entity.NewCardRequestEntity;
import com.fpt.mic.micweb.utils.Constants;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by TriPQM on 07/03/2015.
 */
public class CardBusiness {

    public List<CardEntity> getIssuedCard(int offset, int count) {
        CardInstanceDao cardInstanceDao = new CardInstanceDao();
        return cardInstanceDao.getIssuedCard(offset, count);
    }
    public List<CardInstanceEntity> getIssuedCard(String customerCode, int offset, int count) {
        CardInstanceDao cardDao = new CardInstanceDao();
        return cardDao.getIssuedCard(customerCode, offset, count);
    }

    public Long getIssuedCardCount(String customerCode) {
        CardInstanceDao cardInstanceDao = new CardInstanceDao();
        return cardInstanceDao.getIssuedCardCount(customerCode);
    }
    public Long getIssuedCardCount() {
        CardInstanceDao cardInstanceDao = new CardInstanceDao();
        return cardInstanceDao.getIssuedCardCount();
    }

    public List<CardInstanceEntity> getAllCardInstancesByCardID(String cardId) {
        CardInstanceDao cardInstanceDao = new CardInstanceDao();
        return cardInstanceDao.getAllCardInstancesByCardID(cardId);
    }

    public CardInstanceEntity getLastActiveCardInnstance(String cardId) {
        CardInstanceDao cardInstanceDao = new CardInstanceDao();
        return cardInstanceDao.getLastActiveCardInstanceByCardId(cardId);
    }

    public CardInstanceEntity getCardByContract(String contractCode) {
        CardInstanceDao cardInstanceDao = new CardInstanceDao();
        CardInstanceEntity cardEntity = cardInstanceDao.getActiveCardInstanceByContract(contractCode);
        return cardEntity;
    }
    // return map: key = newCardRequestId, value: new CardId
    public Map<Integer, String> getMappingWithNewCardRequest() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        CardInstanceDao cardInstanceDao = new CardInstanceDao();
        List<CardInstanceEntity> list = cardInstanceDao.getAllCard();
        for (CardInstanceEntity cardEntity : list) {
            map.put(cardEntity.getNewCardRequestId(), cardEntity.getCardId());
        }
        return map;
    }

    // return map: key = old cardId, value: newCardRequestId
    public Map<String, Integer> getMappingOldCardIdAndNewCardRequestId() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        NewCardRequestDao newCardRequestDao = new NewCardRequestDao();
        List<NewCardRequestEntity> list = newCardRequestDao.getAllNewCardRequest();
        for (NewCardRequestEntity newCardRequestEntity : list) {
            map.put(newCardRequestEntity.getMicCardInstanceByOldCardInstanceId().getCardId(),
                    newCardRequestEntity.getId());
        }
        return map;
    }

    public Map<String, String> getMappingWithContract() {
        Map<String, String> map = new HashMap<String, String>();
        CardInstanceDao cardInstanceDao = new CardInstanceDao();
        List<CardInstanceEntity> list = cardInstanceDao.getAllCard();
        for (CardInstanceEntity cardEntity : list) {
            map.put(cardEntity.getCardId(), cardEntity.getContractCode());
        }
        return map;
    }

    public List<CardInstanceEntity> getCardInstancesIncludeDeactive(String contractCode) {
        CardInstanceDao cardInstanceDao = new CardInstanceDao();
        return cardInstanceDao.getCardInstancesByContractIncludeDeactive(contractCode);
    }


    // kiem tra hop dong da co yeu cau the moi chua giai quyet chua
    public boolean isNewCardRequested(String contractCode){
        NewCardRequestDao newCardRequestDao = new NewCardRequestDao();
        return newCardRequestDao.getUnresolveRequest(contractCode) != null;
    }
    // dang ky the moi
    public boolean requestNewCardRequest(NewCardRequestDto newCardRequestDto, boolean isDeliveryRequested, boolean isPaid) {
        NewCardRequestDao newCardRequestDao = new NewCardRequestDao();
        CardInstanceDao cardInstanceDao = new CardInstanceDao();
        NewCardRequestEntity newCardRequestEntity = new NewCardRequestEntity();
        CardInstanceEntity cardEntity = cardInstanceDao.getActiveCardInstanceByContract(newCardRequestDto.getContractCode());
        newCardRequestEntity.setOldCardInstanceId(cardEntity.getId());
        newCardRequestEntity.setNote(newCardRequestDto.getNote());
        newCardRequestEntity.setRequestDate(new Timestamp(new Date().getTime()));
        if (isDeliveryRequested == true) {
            newCardRequestEntity.setIsDeliveryRequested(1);
        }
        if (isPaid == true) {
            newCardRequestEntity.setIsPaid(1);
        }
        NewCardRequestEntity entity = newCardRequestDao.create(newCardRequestEntity);
        if (entity != null) {

            // Send notification
            NewCardRequestEntity notifEntity = newCardRequestDao.read(entity.getId());
            NotificationBusiness bus = new NotificationBusiness();
            bus.send(NotificationBuilder.customerSendNewCardRequest(notifEntity));

            return true;
        }
        return false;
    }
    public void deactiveCardByContractCode(String contractCode) {
        CardInstanceDao cardInstanceDao = new CardInstanceDao();
        CardInstanceEntity cardEntity = cardInstanceDao.getActiveCardInstanceByContract(contractCode);
        cardEntity.setDeactivatedDate(new Timestamp(new Date().getTime()));
        cardInstanceDao.update(cardEntity);
    }

    public List<CardAccessLogEntity> getCardInstanceAccessLog(int cardInstanceId, int offset, int count) {
        CardAccessLogDao cardAccessLogDao = new CardAccessLogDao();
        return cardAccessLogDao.getCardAccessLog(cardInstanceId, offset, count);
    }

    public Long getCardInstanceAccessLogCount(int cardInstanceId) {
        CardAccessLogDao cardAccessLogDao = new CardAccessLogDao();
        return cardAccessLogDao.getCardAccessLogCount(cardInstanceId);
    }

    /**
     * Set status for card to AVAILABLE and ready to be use for other contract
     * @param dto
     */
    public void recycleCard(RecycleCardDto dto) {
        CardDao cardDao = new CardDao();
        CardEntity card = cardDao.read(dto.getCardId());
        card.setStatus(CardEntity.STATUS_AVAILABLE);
        cardDao.update(card);
    }
    public NewCardRequestEntity updatePaidNewCardRequest(String contractCode){
        NewCardRequestEntity newCardRequestEntity;
        NewCardRequestDao newCardRequestDao = new NewCardRequestDao();
        newCardRequestEntity = newCardRequestDao.getUnpaidRequestByContractCode(contractCode);
        newCardRequestEntity.setIsPaid(1);
        return newCardRequestDao.update(newCardRequestEntity);
    }

    public String getCardValidation(CardInstanceEntity card) {

        if (card.getDeactivatedDate() != null ||
                card.getMicContractByContractCode().getStatus()
                        .equalsIgnoreCase(Constants.ContractStatus.CANCELLED) ||
                card.getMicContractByContractCode().getStatus()
                        .equalsIgnoreCase(Constants.ContractStatus.PENDING) ||
                card.getMicContractByContractCode().getStatus()
                        .equalsIgnoreCase(Constants.ContractStatus.NO_CARD)) {
            return "Thẻ không hợp lệ";
        }

        if (card.getMicContractByContractCode().getStatus()
                .equalsIgnoreCase(Constants.ContractStatus.EXPIRED)) {
            return "Thẻ hết hạn";
        }

        if (card.getMicContractByContractCode().getStatus()
                .equalsIgnoreCase(Constants.ContractStatus.READY) ||
                card.getMicContractByContractCode().getStatus()
                        .equalsIgnoreCase(Constants.ContractStatus.REQUEST_CANCEL)) {
            // Check if nearly expired
            if (card.getMicContractByContractCode().getExpiredDate().getTime() - (new Date()).getTime() <
                    Constants.StaffConfiguration.EXPIRED_DATE_AFTER) {
                return "Thẻ sắp hết hạn";
            } else {
                return "Thẻ hợp lệ";
            }
        }
        return "Thẻ không hợp lệ";
    }

}
