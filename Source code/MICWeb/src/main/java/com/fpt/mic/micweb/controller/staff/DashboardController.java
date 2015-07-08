package com.fpt.mic.micweb.controller.staff;

import com.fpt.mic.micweb.controller.common.AuthController;
import com.fpt.mic.micweb.framework.responses.JspPage;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.NotificationBusiness;
import com.fpt.mic.micweb.model.dto.UserDto;
import com.fpt.mic.micweb.model.entity.StaffEntity;
import com.fpt.mic.micweb.model.entity.helper.NotificationEntity;

import javax.servlet.annotation.WebServlet;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

/**
 * Created by dinhquangtrung on 5/23/15.
 */
@WebServlet(name = "DashboardController", urlPatterns = {"/staff", "/staff/dashboard"})
public class DashboardController extends AuthController {
    @Override
    public List<String> getAllowedRoles() {
        return Collections.singletonList(UserDto.ROLE_STAFF);
    }

    public ResponseObject getView(R r) {
        NotificationBusiness notificationBusiness = new NotificationBusiness();
        String staffCode = ((StaffEntity) getLoggedInUser()).getStaffCode();
        List notifications = notificationBusiness.getUnreadNotifications(staffCode, 10);

        BigInteger unreadCount = notificationBusiness.getUnreadNotificationsCount(staffCode);
        r.equest.setAttribute("notifications", notifications);
        r.equest.setAttribute("unreadCount", unreadCount);

        return new JspPage("staff/dashboard.jsp");
    }
}
