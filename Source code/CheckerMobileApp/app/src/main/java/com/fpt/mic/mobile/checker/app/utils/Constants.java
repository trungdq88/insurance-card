package com.fpt.mic.mobile.checker.app.utils;

/**
 * FPT University - Capstone Project - Summer 2015 - CheckerMobileApp
 * Created by dinhquangtrung on 6/8/15.
 */
public class Constants {
    public static final long CONTRACT_NEARLY_EXPIRED_RANGE = 1000 * 86400 * 15; // 15 days

    public static class ContractStatus {
        public static final String PENDING = "Pending";
        public static final String NO_CARD = "No card";
        public static final String READY = "Ready";
        public static final String EXPIRED = "Expired";
        public static final String REQUEST_CANCEL = "Request cancel";
        public static final String CANCELLED = "Cancelled";
    }
    public static final String API_BASE = "http://192.168.43.244:8080/api";
}
