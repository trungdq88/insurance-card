package com.fpt.mic.mobile.checker.app.business;

import com.fpt.mic.mobile.checker.app.ApiRequest.ApiRequest;
import com.fpt.mic.mobile.checker.app.utils.Constants;

/**
 * FPT University - Capstone Project - Summer 2015 - CheckerMobileApp
 * Created by dinhquangtrung on 6/8/15.
 */
public class ApiBusiness {

    public void checkCard(String cardID, final IOnCheckContract cb) {
        ApiRequest apiRequest = new ApiRequest(Constants.API_BASE);
        apiRequest.setParam("action", "checkCard");
        apiRequest.setParam("cardID", cardID);
        apiRequest.get(new ApiRequest.IOnApiResponse() {
            @Override
            public void onResponse(String response) {
                // TODO: process input

                cb.onCheckCardResult(null);
            }
        });
    }

    public interface IOnCheckContract {
        void onCheckCardResult(Object result);
    }
}
