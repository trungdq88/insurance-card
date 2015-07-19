package com.fpt.mic.micweb.framework.responses;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 7/19/15.
 */
public class ErrorPage extends ResponseObject {
    private String msg;
    public ErrorPage(String s) {
        this.msg = s;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
