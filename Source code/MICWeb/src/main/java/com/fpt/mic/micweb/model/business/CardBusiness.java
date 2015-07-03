package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.CardDao;
import com.fpt.mic.micweb.model.dao.helper.NewCardRequestDao;
import com.fpt.mic.micweb.model.dto.form.NewCardRequestDto;
import com.fpt.mic.micweb.model.entity.CardEntity;
import com.fpt.mic.micweb.model.entity.NewCardRequestEntity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by TriPQM on 07/03/2015.
 */
public class CardBusiness {
    public CardEntity getCardByContract(String contractCode){
        NewCardRequestDao newCardRequestDao = new NewCardRequestDao();
        CardDao cardDao = new CardDao();
        CardEntity cardEntity = cardDao.getCardByContract(contractCode);
        return cardEntity;
    }
    public boolean isNewCardRequested(String contractCode){
        NewCardRequestDao newCardRequestDao = new NewCardRequestDao();
        CardEntity cardEntity = getCardByContract(contractCode);
        if (cardEntity != null){
            NewCardRequestEntity newCardRequestEntity = newCardRequestDao.getUnsolvedRequestByCardId(cardEntity.getCardId());
            if (newCardRequestEntity != null){
                return true;
            }
        }
        return false;
    }
    public String requestNewCard(NewCardRequestDto newCardRequestDto){
        NewCardRequestDao newCardRequestDao = new NewCardRequestDao();
        NewCardRequestEntity newCardRequestEntity ;
        CardDao cardDao = new CardDao();
        // kiem tra neu exist card
        CardEntity cardEntity = cardDao.getCardByContract(newCardRequestDto.getContractCode());
        if (cardEntity != null){
            // check neu da yeu cau roi
            newCardRequestEntity = newCardRequestDao.getUnsolvedRequestByCardId(cardEntity.getCardId());
            // neu chua yeu cau thi tao yeu cau moi
            if (newCardRequestEntity == null) {
                newCardRequestEntity = new NewCardRequestEntity();
                newCardRequestEntity.setCustomerCode(newCardRequestDto.getCustomerCode());
                newCardRequestEntity.setOldCardId(cardEntity.getCardId());
                newCardRequestEntity.setNote(newCardRequestDto.getNote());
                newCardRequestEntity.setRequestDate(new Timestamp(new Date().getTime()));
                // create new card request into database
                newCardRequestEntity = newCardRequestDao.create(newCardRequestEntity);
                newCardRequestEntity = newCardRequestDao.getUnsolvedRequestByCardId(cardEntity.getCardId());
                if (newCardRequestEntity != null){
                    // deactive old card
                    cardEntity.setDeactivatedDate(new Timestamp(new Date().getTime()));
                    // add new card request id
                    cardEntity.setNewCardRequestId(newCardRequestEntity.getId());
                    cardDao.update(cardEntity);
                    // thanh cong
                    return null;
                }

            }
            return "Bạn đã yêu cầu thẻ mới trước đó. Vui lòng chờ xử lý";
        }
        return "Hợp đồng chưa có thẻ bảo hiểm";
    }
}
