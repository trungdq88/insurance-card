package com.fpt.mic.micweb.utils;

/**
 * Created by dinhquangtrung on 5/23/15.
 */
public class StringUtils {
    public static String uppercaseFirstLetter(String str) {
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }
}
