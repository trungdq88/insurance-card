package com.fpt.mic.micweb.utils;

import com.fpt.mic.micweb.framework.responses.JsonString;
import org.json.JSONObject;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/13/15.
 */
public class CurrencyUtils {
    private static final String YAHOO_CURRENCY_API = "https://query.yahooapis.com/v1/public/yql?q=select+%2A+from+yahoo.finance.xchange+where+pair+in+%28%22USDVND%22%29&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback";

    public static double getCurrentRate() {
        String json = null;
        try {
            json = NetworkUtils.getPageContentHttps(YAHOO_CURRENCY_API);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(json);
        if (json != null) {
            JSONObject jsonObject = new JSONObject(json);
            String rate = jsonObject.getJSONObject("query").getJSONObject("results").getJSONObject("rate").getString("Rate");
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
