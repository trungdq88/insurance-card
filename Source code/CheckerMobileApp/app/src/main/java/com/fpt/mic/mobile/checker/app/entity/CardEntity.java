package com.fpt.mic.mobile.checker.app.entity;

import java.util.Collection;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 7/8/15.
 */
public class CardEntity {
    public static final int STATUS_READY = 1;
    public static final int STATUS_DEACTIVATED = 0;
    public String cardId;
    public int status;
    public Collection<CardInstanceEntity> micCardInstancesByCardId;
}
