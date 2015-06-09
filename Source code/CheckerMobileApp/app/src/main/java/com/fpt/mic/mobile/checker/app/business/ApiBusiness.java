package com.fpt.mic.mobile.checker.app.business;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpt.mic.mobile.checker.app.ApiRequest.ApiRequest;
import com.fpt.mic.mobile.checker.app.entity.CardEntity;
import com.fpt.mic.mobile.checker.app.utils.Constants;

import java.io.IOException;

/**
 * FPT University - Capstone Project - Summer 2015 - CheckerMobileApp
 * Created by dinhquangtrung on 6/8/15.
 */
public class ApiBusiness {

    ObjectMapper mapper = new ObjectMapper();

    public void checkCard(String cardID, final IOnCheckContract cb) {
        ApiRequest apiRequest = new ApiRequest(Constants.API_BASE);
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
                }
            }
        });
    }

    public interface IOnCheckContract {
        void onCheckCardResult(CardEntity result);
    }
}
