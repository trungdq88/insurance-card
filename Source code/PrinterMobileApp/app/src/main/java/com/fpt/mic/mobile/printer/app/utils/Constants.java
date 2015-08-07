package com.fpt.mic.mobile.printer.app.utils;

/**
 * FPT University - Capstone Project - Summer 2015 - PrinterMobileApp
 * Created by dinhquangtrung on 6/7/15.
 */
public class Constants {
    public static final String APP_PREF_NAME = "com.fpt.mic.mobile.printer.app.prefstore";

    public static class ContractStatus {
        public static final String PENDING = "Pending";
        public static final String NO_CARD = "No card";
        public static final String READY = "Ready";
        public static final String EXPIRED = "Expired";
        public static final String REQUEST_CANCEL = "Request cancel";
        public static final String CANCELLED = "Cancelled";
    }
    public static class StatusColor {
        public static final String PENDING = "#9B9B9B";
        public static final String NO_CARD = "#337ab7";
        public static final String READY = "#5cb85c";
        public static final String EXPIRED = "#d9534f";
        public static final String REQUEST_CANCEL = "#f0ad4e";
        public static final String CANCELLED = "#4C4C4C";
    }
}
