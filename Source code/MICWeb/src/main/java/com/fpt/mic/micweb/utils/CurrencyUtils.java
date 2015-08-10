package com.fpt.mic.micweb.utils;

import org.json.JSONObject;

import java.util.Date;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/13/15.
 */
public class CurrencyUtils {
    // API url for convert USD -> VND
    private static final String YAHOO_CURRENCY_API = "https://query.yahooapis.com/v1/public/yql?q=select+%2A+from+yahoo.finance.xchange+where+pair+in+%28%22USDVND%22%29&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback";
    private static final long CACHE_DURATION = 1000 * 86400; // 1 day
    private static final double DEFAULT_RATE = 21814.50;

    private static double currentRate = 0;
    private static long lastUpdate = (new Date()).getTime();

    /**
     * Get currency from Yahoo API
     * Save the value to cache to reduced load
     * @return
     */
    public static double getCurrentRate() {
        if (currentRate == 0 ||
                (new Date()).getTime() - lastUpdate > CACHE_DURATION) {
            // currentRate = getRateFromAPI();
            currentRate = DEFAULT_RATE;
            lastUpdate = (new Date()).getTime();
        }
        return currentRate;
    }

    private static double getRateFromAPI() {
        System.out.println("Send request to Yahoo Currency API...");
        String json = null;
        try {
            json = NetworkUtils.getPageContentHttps(YAHOO_CURRENCY_API);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(json);
        if (json != null) {
            JSONObject jsonObject = new JSONObject(json);
            String rate = jsonObject.getJSONObject("query")
                    .getJSONObject("results")
                    .getJSONObject("rate")
                    .getString("Rate");
            try {
                return Double.parseDouble(rate);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return 0;
            }
        }
        return 0;
    }
}
