package com.fpt.mic.micweb.controller.common;

import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JsonString;
import com.fpt.mic.micweb.framework.responses.RedirectTo;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.NotificationBusiness;
import com.fpt.mic.micweb.model.entity.helper.IUserEntity;
import com.fpt.mic.micweb.model.entity.helper.NotificationEntity;

import javax.servlet.annotation.WebServlet;
import java.util.List;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 7/3/15.
 */
@WebServlet(name = "NotifyController", urlPatterns = "/notif")
public class NotifyController extends AuthController {
    @Override
    public List<String> getAllowedRoles() {
        return null;
    }

    public ResponseObject getMarkAsRead(R r) {
        if (!isLoggedIn()) return null;

        String userCode = ((IUserEntity) getLoggedInUser()).calcUserCode();

        String idStr = r.equest.getParameter("id");
        String redirect = r.equest.getParameter("redirect");

        if (idStr != null && !idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);

            NotificationBusiness bus = new NotificationBusiness();

            NotificationEntity entity = bus.get(id);

            boolean b = bus.markAsRead(id, userCode);

            if (redirect != null) {
                return new RedirectTo(entity.generateRelatedLink());
            } else {
                return new JsonString(b);
            }
        }

        return new JsonString(false);
    }
    public ResponseObject getMarkAsUnread(R r) {
        if (!isLoggedIn()) return null;

        String userCode = ((IUserEntity) getLoggedInUser()).calcUserCode();

        String idStr = r.equest.getParameter("id");
        String redirect = r.equest.getParameter("redirect");

        if (idStr != null && !idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);

            NotificationBusiness bus = new NotificationBusiness();

            NotificationEntity entity = bus.get(id);

            boolean b = bus.markAsUnread(id, userCode);

            if (redirect != null) {
                return new RedirectTo(entity.generateRelatedLink());
            } else {
                return new JsonString(b);
            }
        }

        return new JsonString(false);
    }
}
