package com.fpt.mic.micweb.model.dto;

import com.fpt.mic.micweb.model.entity.CardInstanceEntity;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 7/17/15.
 */
public class CheckCardResponseDto {
    // "Thẻ hợp lệ"
    public static final int RESULT_VALID_CARD = 0;
    // "Thẻ không hợp lệ"
    public static final int RESULT_INVALID_CARD = 1;
    // "Thẻ hết hạn"
    public static final int RESULT_EXPIRED_CARD = 2;
    // "Thẻ sắp hết hạn"
    public static final int RESULT_NEARLY_EXPIRED_CARD = 3;
    private CardInstanceEntity cardInstanceEntity;
    private int result;

    public CheckCardResponseDto() {
    }
    public CheckCardResponseDto(CardInstanceEntity cardInstanceEntity, int result) {
        this.cardInstanceEntity = cardInstanceEntity;
        this.result = result;
    }

    public CardInstanceEntity getCardInstanceEntity() {
        return cardInstanceEntity;
    }

    public void setCardInstanceEntity(CardInstanceEntity cardInstanceEntity) {
        this.cardInstanceEntity = cardInstanceEntity;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
