package com.fpt.mic.mobile.printer.app.utils;

import android.content.SharedPreferences;
import com.fpt.mic.mobile.printer.app.MainApplication;

/**
 * Created by dinhquangtrung on 10/7/14.
 */
public class PrefStoreUtils {
    private static SharedPreferences getSettings() {
        return MainApplication.getAppContext().getSharedPreferences(Constants.APP_PREF_NAME, 0);
    }
    public static void set(String key, String value) {
        getSettings().edit().putString(key, value).apply();
    }
    public static String get(String key) {
        return getSettings().getString(key, "");
    }
    public static String get(String key, String defaultValue) {
        return getSettings().getString(key, defaultValue);
    }
    public static int get(String key, int defaultValue) {
        return getSettings().getInt(key, defaultValue);
    }

    public static void set(String key, int value) {
        getSettings().edit().putInt(key, value).apply();
    }
    public static void del(String key) {
        getSettings().edit().remove(key).apply();
    }
}
