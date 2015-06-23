package com.fpt.mic.micweb.framework;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dinhquangtrung on 5/23/15.
 * This class is a intermediate class to contains HttpServletRequest and HttpServletResponse,
 * Why? Because it's short.
 */
public class R {
    public HttpServletRequest equest;
    public HttpServletResponse esponse;
    public FormReader ead = new FormReader();

    public R(HttpServletRequest equest, HttpServletResponse esponse) {
        this.ead.request = equest;
        this.equest = equest;
        this.esponse = esponse;
    }
}
