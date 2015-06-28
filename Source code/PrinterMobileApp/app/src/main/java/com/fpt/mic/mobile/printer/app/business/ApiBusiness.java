package com.fpt.mic.mobile.printer.app.business;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpt.mic.mobile.printer.app.ApiRequest.ApiRequest;
import com.fpt.mic.mobile.printer.app.utils.Constants;
import com.fpt.mic.mobile.printer.app.utils.Settings;

import java.io.IOException;

/**
 * FPT University - Capstone Project - Summer 2015 - PrinterMobileApp
 * Created by dinhquangtrung on 6/10/15.
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

    public interface IOnConnectionResult {
        void onConnectionResult(boolean result);
    }
}
