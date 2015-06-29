package com.fpt.mic.mobile.checker.app.utils;

/**
 * FPT University - Capstone Project - Summer 2015 - app
 * Created by dinhquangtrung on 6/27/15.
 */
public class Settings {
    // Default emulator to host IP
    public static String serverIp = "10.0.2.2";
    // Connect to localhost via simulator: 10.0.2.2
    public static String getApiBase() {
        return "http://" + Settings.serverIp + ":8080/api";
    }
}
