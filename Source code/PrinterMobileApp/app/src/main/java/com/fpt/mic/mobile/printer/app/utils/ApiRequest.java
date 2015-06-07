package com.fpt.mic.mobile.printer.app.utils;

/**
 * FPT University - Capstone Project - Summer 2015 - PrinterMobileApp
 * Created by dinhquangtrung on 6/7/15.
 */
public class ApiRequest {
    DataSource dataSource = new DataSource();
    RequestSender sender = new RequestSender();

    public ApiRequest(String destination) {
        dataSource.setLink(destination);
    }

    public void setApiDestination(String url) {
        dataSource.setLink(url);
    }

    public void setParam(String param, String value) {
        dataSource.setVar(param, value);
    }

    public void get(final IOnApiResponse cb) {
        sender.start(dataSource.getSecureURL(), new RequestSender.IRequestSenderComplete() {
            @Override
            public void onRequestComplete(String result) {
                cb.onResponse(result);
            }
        });
    }

    public interface IOnApiResponse {
        void onResponse(String response);
    }
}
