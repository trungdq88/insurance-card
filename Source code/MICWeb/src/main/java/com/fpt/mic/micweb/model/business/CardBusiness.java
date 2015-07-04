package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.CardDao;
import com.fpt.mic.micweb.model.dao.helper.NewCardRequestDao;
import com.fpt.mic.micweb.model.dto.NotificationBuilder;
import com.fpt.mic.micweb.model.dto.form.NewCardRequestDto;
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
    public CardEntity getCardByContract(String contractCode){
        CardDao cardDao = new CardDao();
        CardEntity cardEntity = cardDao.getCardByContract(contractCode);
        return cardEntity;
    }
    public Map<Integer,String> getMappingWithNewCardRequest(){
        Map<Integer,String> map = new HashMap<Integer, String>();
        CardDao cardDao = new CardDao();
        List<CardEntity> list = cardDao.getAllCard();
        for (CardEntity cardEntity : list) {
            map.put(cardEntity.getNewCardRequestId(),cardEntity.getCardId());
        }
        return map;
    }
    public List getCardByContractIncludeDeactive(String contractCode){
        CardDao cardDao = new CardDao();
        List list = cardDao.getCardByContractIncludeDeactive(contractCode);
        return list;
    }
    public boolean isNewCardRequested(CardEntity cardEntity){
        NewCardRequestDao newCardRequestDao = new NewCardRequestDao();
        if (cardEntity != null){
            NewCardRequestEntity newCardRequestEntity = newCardRequestDao.getUnsolvedRequestByCardId(cardEntity.getCardId());
            if (newCardRequestEntity != null){
                return true;
            }
        }
        return false;
    }
    public boolean requestNewCardRequest(NewCardRequestDto newCardRequestDto){
        NewCardRequestDao newCardRequestDao = new NewCardRequestDao();
        CardDao cardDao = new CardDao();
        NewCardRequestEntity newCardRequestEntity  = new NewCardRequestEntity();
        CardEntity cardEntity = cardDao.getCardByContract(newCardRequestDto.getContractCode());
        newCardRequestEntity.setCustomerCode(newCardRequestDto.getCustomerCode());
        newCardRequestEntity.setOldCardId(cardEntity.getCardId());
        newCardRequestEntity.setNote(newCardRequestDto.getNote());
        newCardRequestEntity.setRequestDate(new Timestamp(new Date().getTime()));
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
}
