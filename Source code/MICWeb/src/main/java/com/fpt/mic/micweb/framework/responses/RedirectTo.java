package com.fpt.mic.micweb.framework.responses;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/10/15.
 */
public class RedirectTo extends ResponseObject {
    private String url;

    public RedirectTo(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
