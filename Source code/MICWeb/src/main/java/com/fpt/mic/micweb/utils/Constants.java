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
        public static final int PAYMENT_DUE_DATE = 7;
        public static final int RENEW_DUE_DATE = 7;
        public static final int NEARLY_EXCEED_EXPIRED_1 = 15;
        public static final int NEARLY_EXCEED_EXPIRED_2 = 8;
        public static final int NEARLY_EXCEED_EXPIRED_3 = 3;
    }

    public static class StaffConfiguration {
        public static final int START_DATE_BEFORE = 7;
        public static final int START_DATE_AFTER = 60;
        public static final int EXPIRED_DATE_AFTER = 1;
        public static final int CONTRACT_DEFAULT_TERM = 1;
        public static final int PAID_DATE_BEFORE = 7;
        public static final int PAID_DATE_AFTER = 7;
        public static final int CANCEL_DATE_BEFORE = 7;
        public static final int CANCEL_DATE_AFTER = 30;
    }
    public static class PaymentFee {
        public static final float NEW_CARD_REQUEST_FEE = 50000;
        public static final float DELIVERY_FEE = 10000;
    }

    public static class Session {
        public static final String USER_DTO = "userDto";
        public static final String CONCURRENCY = "concurrency_";
    }
}
