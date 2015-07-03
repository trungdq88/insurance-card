package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.helper.NotificationDao;
import com.fpt.mic.micweb.model.entity.helper.NotificationEntity;

import java.math.BigInteger;
import java.util.List;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 7/3/15.
 */
public class NotificationBusiness {
    public NotificationEntity sendNotification(NotificationEntity notificationEntity) {
        NotificationDao notificationDao = new NotificationDao();
        return notificationDao.create(notificationEntity);
    }

    public List getNotifications(String code) {
        NotificationDao notificationDao = new NotificationDao();
        return notificationDao.getAllNotifications(code);
    }

    public BigInteger getUnreadNotificationsCount(String code) {
        NotificationDao notificationDao = new NotificationDao();
        return notificationDao.getUnreadNotificationsCount(code);
    }
}
