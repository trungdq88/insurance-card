package com.fpt.mic.micweb.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
    /*
    Reference: http://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
