package com.fpt.mic.micweb.controller.test;

import com.fpt.mic.micweb.framework.BasicController;
import com.fpt.mic.micweb.framework.R;
import com.fpt.mic.micweb.framework.responses.JsonString;
import com.fpt.mic.micweb.framework.responses.ResponseObject;
import com.fpt.mic.micweb.model.business.NotificationBusiness;
import com.fpt.mic.micweb.model.dao.ContractDao;
import com.fpt.mic.micweb.model.dto.NotificationBuilder;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.model.entity.helper.NotificationEntity;

import javax.servlet.annotation.WebServlet;
import java.math.BigInteger;
import java.util.List;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 7/3/15.
 */
@WebServlet(name = "TestNotification", urlPatterns = "/testnotif")
public class TestNotification extends BasicController {
    public ResponseObject getView(R r) {
        ContractDao dao = new ContractDao();
        ContractEntity contract = dao.read("HD0003");
        NotificationBusiness notificationBusiness = new NotificationBusiness();
        boolean isSuccess = notificationBusiness
                .send(NotificationBuilder.customerCreateContract(contract));
        return new JsonString(isSuccess);
    }

    public ResponseObject getList(R r) {
        NotificationBusiness notificationBusiness = new NotificationBusiness();
        List notifications = notificationBusiness.getNotifications("NV0923");
        return new JsonString(notifications);
    }

    public ResponseObject getUnread(R r) {
        NotificationBusiness notificationBusiness = new NotificationBusiness();
        BigInteger count = notificationBusiness.getUnreadNotificationsCount("NV0001");
        return new JsonString(count);
    }

    public static void main(String[] args) {
        int web = 1;
        int email = 2;
        int weborandemail = 1 & 2;

        System.out.println(weborandemail);

         int target = 1;
        // int target = 2;
        // int target = 1 & 2;

        if ((target & web) == target) {
            System.out.println("Its a web");
        }
        if ((target & email) == target) {
            System.out.println("Its a email");
        }
    }
}
