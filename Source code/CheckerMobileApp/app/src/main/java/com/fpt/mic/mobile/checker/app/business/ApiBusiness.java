package com.fpt.mic.mobile.checker.app.business;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpt.mic.mobile.checker.app.ApiRequest.ApiRequest;
import com.fpt.mic.mobile.checker.app.entity.CardEntity;
import com.fpt.mic.mobile.checker.app.utils.Constants;
import com.fpt.mic.mobile.checker.app.utils.Settings;

import java.io.IOException;

/**
 * FPT University - Capstone Project - Summer 2015 - CheckerMobileApp
 * Created by dinhquangtrung on 6/8/15.
 */
public class ApiBusiness {

    ObjectMapper mapper = new ObjectMapper();

    /**
     * Checking connection to API server
     * @param cb
     */
    public void checkConnection(final IOnConnectionResult cb) {
        ApiRequest apiRequest = new ApiRequest(Settings.getApiBase());
        apiRequest.setParam("action", "checkConnection");
        apiRequest.get(new ApiRequest.IOnApiResponse() {
            @Override
            public void onResponse(String response) {
                try {
                    cb.onConnectionResult(mapper.readValue(response, Boolean.class));
                } catch (IOException e) {
                    e.printStackTrace();
                    cb.onConnectionResult(false);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    cb.onConnectionResult(false);
                }
            }
        });
    }

    public void checkCard(String cardID, final IOnCheckContract cb) {
        ApiRequest apiRequest = new ApiRequest(Settings.getApiBase());
        apiRequest.setParam("action", "checkCard");
        apiRequest.setParam("cardID", cardID);
        apiRequest.get(new ApiRequest.IOnApiResponse() {
            @Override
            public void onResponse(String response) {
                try {
                    CardEntity card = mapper.readValue(response, CardEntity.class);
                    cb.onCheckCardResult(card);
                } catch (IOException e) {
                    e.printStackTrace();
                    cb.onCheckCardResult(null);
                }
            }
        });
    }

    public interface IOnCheckContract {
        void onCheckCardResult(CardEntity result);
    }

    public interface IOnConnectionResult {
        void onConnectionResult(boolean result);
    }
}
