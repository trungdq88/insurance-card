package com.fpt.mic.micweb.controller.common;

import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JsonString;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.AjaxBusiness;
import com.fpt.mic.micweb.model.business.CustomerBusiness;
import com.fpt.mic.micweb.model.business.NotificationBusiness;
import com.fpt.mic.micweb.model.business.StaffBusiness;
import com.fpt.mic.micweb.model.entity.PaymentEntity;
import com.fpt.mic.micweb.model.entity.helper.IUserEntity;

import javax.servlet.annotation.WebServlet;
import java.math.BigInteger;
import java.util.List;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/29/15.
 */
@WebServlet(name = "AjaxController", urlPatterns = {"/ajax"})
public class AjaxController extends AuthController {

    @Override
    public List<String> getAllowedRoles() {
        return null;
    }

    /**
     * Resend password for customer
     * TODO: security this
     *
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

        boolean b = ajaxBusiness.resendPassword(customerCode, loginUrl);
        return new JsonString(b);
    }


    public ResponseObject getPaymentDetail(R r) {
        Integer paymentId = Integer.parseInt(r.equest.getParameter("paymentId"));

        StaffBusiness staffBusiness = new StaffBusiness();
        PaymentEntity paymentEntity = staffBusiness.getPaymentDetail(paymentId);
        return new JsonString(paymentEntity);
    }

    public ResponseObject postRejectChangePassword(R r) {
        boolean result = false;
        String customerCode = r.equest.getParameter("customerCode");
        CustomerBusiness customerBusiness = new CustomerBusiness();
        result = customerBusiness.rejectChangePassword(customerCode);
        return new JsonString(result);
    }

    public ResponseObject getUnreadCount(R r) {
        if (!isLoggedIn()) return null;

        String userCode = ((IUserEntity) getLoggedInUser()).calcUserCode();

        NotificationBusiness bus = new NotificationBusiness();
        BigInteger count = bus.getUnreadNotificationsCount(userCode);
        return new JsonString(count);
    }

    public ResponseObject getListUnread(R r) {
        if (!isLoggedIn()) return null;

        String userCode = ((IUserEntity) getLoggedInUser()).calcUserCode();

        NotificationBusiness bus = new NotificationBusiness();
        List unreadNotifications = bus.getNotifications(userCode, 10);
        return new JsonString(unreadNotifications);
    }

    public ResponseObject getLoadCustomers(R r) {
        String keyword = r.equest.getParameter("keyword");
        if (keyword == null) keyword = "";
        AjaxBusiness ajaxBusiness = new AjaxBusiness();
        List result = ajaxBusiness.loadCustomers(keyword);
        return new JsonString(result);
    }

    public ResponseObject getLoadContracts(R r) {
        String keyword = r.equest.getParameter("keyword");
        if (keyword == null) keyword = "";
        AjaxBusiness ajaxBusiness = new AjaxBusiness();
        List result = ajaxBusiness.loadContracts(keyword);
        return new JsonString(result);
    }
}