package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.CardAccessLogDao;
import com.fpt.mic.micweb.model.dao.CardDao;
import com.fpt.mic.micweb.model.dao.helper.NewCardRequestDao;
import com.fpt.mic.micweb.model.dto.NotificationBuilder;
import com.fpt.mic.micweb.model.dto.form.NewCardRequestDto;
import com.fpt.mic.micweb.model.entity.CardAccessLogEntity;
import com.fpt.mic.micweb.model.entity.CardEntity;
import com.fpt.mic.micweb.model.entity.NewCardRequestEntity;

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
        CardDao cardDao = new CardDao();
        return cardDao.getIssuedCard(offset, count);
    }
    public List<CardEntity> getIssuedCard(String customerCode, int offset, int count) {
        CardDao cardDao = new CardDao();
        return cardDao.getIssuedCard(customerCode, offset, count);
    }

    public Long getIssuedCardCount() {
        CardDao cardDao = new CardDao();
        return cardDao.getIssuedCardCount();
    }

    public Long getIssuedCardCount(String customerCode) {
        CardDao cardDao = new CardDao();
        return cardDao.getIssuedCardCount(customerCode);
    }

    public CardEntity getCardDetail(String cardId) {
        CardDao cardDao = new CardDao();
        return cardDao.read(cardId);
    }

    public CardEntity getCardByContract(String contractCode) {
        CardDao cardDao = new CardDao();
        CardEntity cardEntity = cardDao.getCardByContract(contractCode);
        return cardEntity;
    }
    // return map: key = newCardRequestId, value: new CardId
    public Map<Integer, String> getMappingWithNewCardRequest() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        CardDao cardDao = new CardDao();
        List<CardEntity> list = cardDao.getAllCard();
        for (CardEntity cardEntity : list) {
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
            map.put(newCardRequestEntity.getOldCardId(),newCardRequestEntity.getId());
        }
        return map;
    }

    public Map<String, String> getMappingWithContract() {
        Map<String, String> map = new HashMap<String, String>();
        CardDao cardDao = new CardDao();
        List<CardEntity> list = cardDao.getAllCard();
        for (CardEntity cardEntity : list) {
            map.put(cardEntity.getCardId(), cardEntity.getContractCode());
        }
        return map;
    }

    public List getCardByContractIncludeDeactive(String contractCode) {
        CardDao cardDao = new CardDao();
        List list = cardDao.getCardByContractIncludeDeactive(contractCode);
        return list;
    }


    // kiem tra hop dong da co yeu cau the moi chua giai quyet chua
    public boolean isNewCardRequestedByContractCode(String contractCode){
        NewCardRequestDao newCardRequestDao = new NewCardRequestDao();

        if(newCardRequestDao.getUnresolveRequestByContractCode(contractCode) == null){
            return false;
        }
        return true;
    }
    // dang ky the moi
    public boolean requestNewCardRequest(NewCardRequestDto newCardRequestDto, boolean isDeliveryRequested, boolean isPaid) {
        NewCardRequestDao newCardRequestDao = new NewCardRequestDao();
        CardDao cardDao = new CardDao();
        NewCardRequestEntity newCardRequestEntity = new NewCardRequestEntity();
        CardEntity cardEntity = cardDao.getLastCardByContract(newCardRequestDto.getContractCode());
        newCardRequestEntity.setOldCardId(cardEntity.getCardId());
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
        CardDao cardDao = new CardDao();
        CardEntity cardEntity = cardDao.getCardByContract(contractCode);
        cardEntity.setDeactivatedDate(new Timestamp(new Date().getTime()));
        cardDao.update(cardEntity);
    }

    public List<CardAccessLogEntity> getCardAccessLog(String cardId, int offset, int count) {
        CardAccessLogDao cardAccessLogDao = new CardAccessLogDao();
        return cardAccessLogDao.getCardAccessLog(cardId, offset, count);
    }

    public Long getCardAccessLogCount(String cardId) {
        CardAccessLogDao cardAccessLogDao = new CardAccessLogDao();
        return cardAccessLogDao.getCardAccessLogCount(cardId);
    }
    public NewCardRequestEntity updatePaidNewCardRequest(String contractCode){
        NewCardRequestEntity newCardRequestEntity;
        NewCardRequestDao newCardRequestDao = new NewCardRequestDao();
        newCardRequestEntity = newCardRequestDao.getUnresolveRequestByContractCode(contractCode);
        newCardRequestEntity.setIsPaid(1);
        return newCardRequestDao.update(newCardRequestEntity);
    }
}
