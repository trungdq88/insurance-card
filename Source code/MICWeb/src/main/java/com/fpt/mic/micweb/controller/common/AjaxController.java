package com.fpt.mic.micweb.controller.common;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JsonString;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.AjaxBusiness;

import javax.servlet.annotation.WebServlet;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/29/15.
 */
@WebServlet(name = "AjaxController", urlPatterns = {"/ajax"})
public class AjaxController extends BasicController {

    /**
     * Resend password for customer
     * TODO: security this
     * @param r
     * @return
     */
    public ResponseObject postResendPassword(R r) {
        String customerCode = r.equest.getParameter("customerCode");

        AjaxBusiness ajaxBusiness = new AjaxBusiness();

        String loginUrl = r.equest.getScheme() +
                "://" + r.equest.getServerName() +
                ":" + r.equest.getServerPort() +
                r.equest.getContextPath() +
                "/user?action=login";

        boolean b = ajaxBusiness.resendPassword(getServletContext(), customerCode, loginUrl);
        return new JsonString(b);
    }
}
