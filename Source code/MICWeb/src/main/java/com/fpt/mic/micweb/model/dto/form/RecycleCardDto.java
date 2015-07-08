package com.fpt.mic.micweb.model.dto.form;

import com.fpt.mic.micweb.model.dao.CardDao;
import com.fpt.mic.micweb.model.entity.CardEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 7/8/15.
 */
public class RecycleCardDto {
    @NotEmpty(message = "Card ID không được để trống")
    String cardId;

    @AssertTrue(message = "Card ID không tồn tại hoặc trạng thái không hợp lệ!")
    private boolean isCardIdValid() {
        if (cardId != null) {
            CardDao cardDao = new CardDao();
            CardEntity card = cardDao.read(cardId);

            // Not exists
            if (card == null) return false;

            // Not valid state
            if (card.getStatus() == CardEntity.STATUS_AVAILABLE) {
                return false;
            }

            return true;
        }
        return false;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}
