package com.fpt.mic.mobile.checker.app.business;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpt.mic.mobile.checker.app.ApiRequest.ApiRequest;
import com.fpt.mic.mobile.checker.app.entity.CardInstanceEntity;
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
                    CardInstanceEntity card = mapper.readValue(response, CardInstanceEntity.class);
                    cb.onCheckCardResult(card);
                } catch (IOException e) {
                    e.printStackTrace();
                    cb.onCheckCardResult(null);
                }
            }
        });
    }

    /**
     * Send punishment information to server
     * @param title
     * @param photo
     */
    public void sendPunishment(String contractCode, String title, String photo, final IOnPunishmentResult cb) {
        ApiRequest apiRequest = new ApiRequest(Settings.getApiBase());
        apiRequest.setParam("action", "updatePunishment");
        apiRequest.setParam("contractCode", contractCode);
        apiRequest.setParam("title", title);
        apiRequest.setParam("photo", photo);
        apiRequest.get(new ApiRequest.IOnApiResponse() {
            @Override
            public void onResponse(String response) {
                try {
                    Boolean result = mapper.readValue(response, Boolean.class);
                    cb.onPunishmentResult(result);
                } catch (IOException e) {
                    e.printStackTrace();
                    cb.onPunishmentResult(false);
                }
            }
        });
    }

    public interface IOnCheckContract {
        void onCheckCardResult(CardInstanceEntity result);
    }

    public interface IOnConnectionResult {
        void onConnectionResult(boolean result);
    }

    public interface IOnPunishmentResult {
        void onPunishmentResult(boolean result);
    }
}
