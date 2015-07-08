package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.CardAccessLogDao;
import com.fpt.mic.micweb.model.dao.CardInstanceDao;
import com.fpt.mic.micweb.model.dao.helper.NewCardRequestDao;
import com.fpt.mic.micweb.model.dto.NotificationBuilder;
import com.fpt.mic.micweb.model.dto.form.NewCardRequestDto;
import com.fpt.mic.micweb.model.entity.CardAccessLogEntity;
import com.fpt.mic.micweb.model.entity.CardInstanceEntity;
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

    public List<CardInstanceEntity> getIssuedCard(int offset, int count) {
        CardInstanceDao cardInstanceDao = new CardInstanceDao();
        return cardInstanceDao.getIssuedCard(offset, count);
    }

    public Long getIssuedCardCount() {
        CardInstanceDao cardInstanceDao = new CardInstanceDao();
        return cardInstanceDao.getIssuedCardCount();
    }

    public CardInstanceEntity getActiveCardInstnace(String cardId) {
        CardInstanceDao cardInstanceDao = new CardInstanceDao();
        return cardInstanceDao.getActiveCardInstanceByContract(cardId);
    }

    public CardInstanceEntity getCardByContract(String contractCode) {
        CardInstanceDao cardInstanceDao = new CardInstanceDao();
        CardInstanceEntity cardEntity = cardInstanceDao.getActiveCardInstanceByContract(contractCode);
        return cardEntity;
    }

    public Map<Integer, String> getMappingWithNewCardRequest() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        CardInstanceDao cardInstanceDao = new CardInstanceDao();
        List<CardInstanceEntity> list = cardInstanceDao.getAllCard();
        for (CardInstanceEntity cardEntity : list) {
            map.put(cardEntity.getNewCardRequestId(), cardEntity.getCardId());
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

    public List getCardInstancesIncludeDeactive(String contractCode) {
        CardInstanceDao cardInstanceDao = new CardInstanceDao();
        List list = cardInstanceDao.getCardInstancesByContractIncludeDeactive(contractCode);
        return list;
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

    public List<CardAccessLogEntity> getCardAccessLog(String cardId, int offset, int count) {
        CardAccessLogDao cardAccessLogDao = new CardAccessLogDao();
        return cardAccessLogDao.getCardAccessLog(cardId, offset, count);
    }

    public Long getCardAccessLogCount(String cardId) {
        CardAccessLogDao cardAccessLogDao = new CardAccessLogDao();
        return cardAccessLogDao.getCardAccessLogCount(cardId);
    }
}
