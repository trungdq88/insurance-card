package com.fpt.mic.micweb.controller.customer;

import com.fpt.mic.micweb.controller.common.AuthController;
import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.NotificationBusiness;
import com.fpt.mic.micweb.model.dto.UserDto;
import com.fpt.mic.micweb.model.entity.CustomerEntity;
import com.fpt.mic.micweb.model.entity.StaffEntity;

import javax.servlet.annotation.WebServlet;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 7/4/15.
 */
@WebServlet(name = "CustomerDashboardController", urlPatterns = "/customer/dashboard")
public class DashboardController extends AuthController {
    public ResponseObject getView(R r) {
        NotificationBusiness notificationBusiness = new NotificationBusiness();
        String customerCode = ((CustomerEntity) getLoggedInUser()).getCustomerCode();
        List notifications = notificationBusiness.getNotifications(
                customerCode);

        BigInteger unreadCount = notificationBusiness.getUnreadNotificationsCount(customerCode);

        r.equest.setAttribute("notifications", notifications);
        r.equest.setAttribute("unreadCount", unreadCount);

        return new JspPage("customer/dashboard.jsp");
    }

    @Override
    public List<String> getAllowedRoles() {
        return Collections.singletonList(UserDto.ROLE_CUSTOMER);
    }
}
