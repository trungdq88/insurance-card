package com.fpt.mic.mobile.printer.app.utils;

/**
 * FPT University - Capstone Project - Summer 2015 - PrinterMobileApp
 * Created by dinhquangtrung on 6/7/15.
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
    // Connect to localhost via simulator: 10.0.2.2
    public static final String API_BASE = "http://192.168.43.244:8080/api";
}
