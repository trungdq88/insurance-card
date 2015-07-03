package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.helper.NotificationDao;
import com.fpt.mic.micweb.model.dao.helper.NotificationReadDao;
import com.fpt.mic.micweb.model.entity.helper.NotificationEntity;
import com.fpt.mic.micweb.model.entity.helper.NotificationReadEntity;

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

    public List getUnreadNotifications(String userCode, int size) {
        NotificationDao notificationDao = new NotificationDao();
        return notificationDao.getUnreadNotifications(userCode, size);
    }

    public boolean markAsRead(int id, String userCode) {
        NotificationReadDao dao = new NotificationReadDao();

        if (dao.get(id, userCode) != null) return true;

        NotificationReadEntity entity = new NotificationReadEntity();
        entity.setNotificationId(id);
        entity.setUserCode(userCode);
        entity.setIsRead(1);

        return dao.create(entity) != null;
    }

    public boolean markAsUnread(int id, String userCode) {
        NotificationReadDao dao = new NotificationReadDao();

        NotificationReadEntity entity = dao.get(id, userCode);
        if (entity == null) return true;
        dao.delete(entity);
        return dao.get(id, userCode) == null;
    }

    public NotificationEntity get(int id) {
        NotificationDao dao = new NotificationDao();
        return dao.get(id);
    }
}
