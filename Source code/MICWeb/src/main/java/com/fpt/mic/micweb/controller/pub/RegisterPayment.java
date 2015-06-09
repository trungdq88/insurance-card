package com.fpt.mic.micweb.controller.pub;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.responses.ResponseObject;

import javax.servlet.annotation.WebServlet;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/9/15.
 */
@WebServlet(name = "RegisterPayment", urlPatterns = {"/public/registerPayment"})
public class RegisterPayment extends BasicController {
    public ResponseObject getView(R r) {
        return new JspPage("public/register-payment.jsp");
    }
}
