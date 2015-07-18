package com.fpt.mic.mobile.checker.app.entity;

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
    public CardInstanceEntity cardInstanceEntity;
    public int result;

    public CheckCardResponseDto() {
    }

    public CheckCardResponseDto(CardInstanceEntity cardInstanceEntity, int result) {
        this.cardInstanceEntity = cardInstanceEntity;
        this.result = result;
    }
}
