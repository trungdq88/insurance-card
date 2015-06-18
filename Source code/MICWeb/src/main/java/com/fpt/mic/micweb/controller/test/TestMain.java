package com.fpt.mic.micweb.controller.test;

import java.security.SecureRandom;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/17/15.
 */
public class TestMain {
    public static void main(String[] args) {
        SecureRandom sr = new SecureRandom();
        byte[] rndBytes = new byte[7];
        sr.nextBytes(rndBytes);

        StringBuilder sb = new StringBuilder();
        for (byte b : rndBytes) {
            sb.append(String.format("%02X", b));
        }

        System.out.println(sb + " ("+sb.length()+")");
    }
}
