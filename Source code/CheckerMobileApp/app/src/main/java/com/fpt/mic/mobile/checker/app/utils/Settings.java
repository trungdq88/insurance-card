package com.fpt.mic.mobile.checker.app.utils;

/**
 * FPT University - Capstone Project - Summer 2015 - app
 * Created by dinhquangtrung on 6/27/15.
 */
public class Settings {
    // Connect to localhost via simulator: 10.0.2.2
    public static String getApiBase() {
        return "http://" + getServerIp() + ":8080/api";
    }

    public static String getServerIp() {
        String defaultIp = "10.0.2.2";
        return PrefStoreUtils.get("serverIp", defaultIp);
    }
    public static void setServerIp(String serverIp) {
        PrefStoreUtils.set("serverIp", serverIp);
    }
}
