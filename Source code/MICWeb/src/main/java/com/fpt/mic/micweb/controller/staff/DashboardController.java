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
import java.util.Collections;
import java.util.List;

/**
 * Created by dinhquangtrung on 5/23/15.
 */
@WebServlet(name = "DashboardController", urlPatterns = {"/staff"})
public class DashboardController extends AuthController {
    @Override
    public List<String> getAllowedRoles() {
        return Collections.singletonList(UserDto.ROLE_STAFF);
    }

    public ResponseObject getView(R r) {
        NotificationBusiness notificationBusiness = new NotificationBusiness();
        List notifications = notificationBusiness.getNotifications(
                ((StaffEntity) getLoggedInUser()).getStaffCode());

        r.equest.setAttribute("notifications", notifications);

        return new JspPage("staff/dashboard.jsp");
    }
}
