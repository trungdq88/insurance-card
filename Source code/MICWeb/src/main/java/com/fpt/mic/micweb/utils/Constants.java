package com.fpt.mic.micweb.utils;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/10/15.
 */
public class Constants {
    public static class ContractStatus {
        public static final String PENDING = "Pending";
        public static final String NO_CARD = "No card";
        public static final String READY = "Ready";
        public static final String EXPIRED = "Expired";
        public static final String REQUEST_CANCEL = "Request cancel";
        public static final String CANCELLED = "Cancelled";
    }
    public static class DueDate {
        public static final int PAYMENT_DUE_DATE = 20;
        public static final int RENEW_DUE_DATE = 90; // change later
    }
    public static class Session {
        public static final String USER_DTO = "userDto";
    }
}
