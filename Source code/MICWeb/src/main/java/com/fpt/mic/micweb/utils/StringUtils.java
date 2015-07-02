package com.fpt.mic.micweb.utils;

import sun.security.provider.MD5;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 * Created by dinhquangtrung on 5/23/15.
 */
public class StringUtils {
    private static SecureRandom random = new SecureRandom();

    public static String randomString() {
        return new BigInteger(130, random).toString(32).substring(0, 6).toUpperCase();
    }
    public static String uppercaseFirstLetter(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static String getString(InputStream is) {
        try {
            int ch;
            StringBuilder sb = new StringBuilder();
            while ((ch = is.read()) != -1)
                sb.append((char) ch);

            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Cannot read mail template!");
        return null;
    }

    public static String unAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
//        return pattern.matcher(temp).replaceAll("");
        return pattern.matcher(temp).replaceAll("").replaceAll("Đ", "D").replace("đ", "d");
    }

    public static String getMD5Hash(String s) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(StandardCharsets.UTF_8.encode(s));
            return String.format("%032x", new BigInteger(1, md5.digest()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
