package com.fpt.mic.micweb.utils;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/6/15.
 */
public class NumberUtils {
    public static void main(String[] args) {
        System.out.printf("Hello: " + String.format("%03s", "01"));
    }
    public static String base10to36(int n) {
        return Integer.toString(n, 36).toUpperCase();
    }

    public static Integer base36to10(String n) {
        return Integer.parseInt(n, 36);
    }
}
