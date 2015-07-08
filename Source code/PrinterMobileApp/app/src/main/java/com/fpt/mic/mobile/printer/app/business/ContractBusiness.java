package com.fpt.mic.mobile.printer.app.business;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpt.mic.mobile.printer.app.dto.ContractSearchResult;
import com.fpt.mic.mobile.printer.app.entity.CardInstanceEntity;
import com.fpt.mic.mobile.printer.app.ApiRequest.ApiRequest;
import com.fpt.mic.mobile.printer.app.utils.Settings;

import java.io.IOException;
import java.util.List;

/**
 * FPT University - Capstone Project - Summer 2015 - PrinterMobileApp
 * Created by dinhquangtrung on 6/7/15.
 */
public class ContractBusiness {

    ObjectMapper mapper = new ObjectMapper();

    public void searchContracts(String keyword, final IOnSearchResult cb) {
        ApiRequest apiRequest = new ApiRequest(Settings.getApiBase());
        apiRequest.setParam("action", "contracts");
        apiRequest.setParam("keyword", keyword);
        apiRequest.get(new ApiRequest.IOnApiResponse() {
            @Override
            public void onResponse(String response) {
                try {
                    List<ContractSearchResult> results =
                            mapper.readValue(response,
                                    new TypeReference<List<ContractSearchResult>>() {});

                    cb.onSearchResult(results);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void updateCardForContract(String contractCode, String cardID, final IOnApiResult cb) {
        ApiRequest apiRequest = new ApiRequest(Settings.getApiBase());
        apiRequest.setParam("action", "updateCardID");
        apiRequest.setParam("contractCode", contractCode);
        apiRequest.setParam("cardID", cardID);
        apiRequest.get(new ApiRequest.IOnApiResponse() {
            @Override
            public void onResponse(String response) {
                try {
                    cb.onApiResult(mapper.readValue(response, CardInstanceEntity.class));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public interface IOnSearchResult {
        void onSearchResult(List<ContractSearchResult> results);
    }
    public interface IOnApiResult {
        void onApiResult(CardInstanceEntity result);
    }
}
