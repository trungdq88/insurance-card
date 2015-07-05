package com.fpt.mic.micweb.model.business;

import com.fpt.mic.micweb.model.dao.helper.NotificationDao;
import com.fpt.mic.micweb.model.dao.helper.NotificationReadDao;
import com.fpt.mic.micweb.model.dto.NotificationDto;
import com.fpt.mic.micweb.model.entity.helper.NotificationEntity;
import com.fpt.mic.micweb.model.entity.helper.NotificationReadEntity;
import com.fpt.mic.micweb.utils.EmailTemplate;
import com.fpt.mic.micweb.utils.EmailUtils;

import java.math.BigInteger;
import java.util.List;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 7/3/15.
 */
public class NotificationBusiness {
    public boolean send(NotificationEntity entity) {
        return send(entity, null);
    }

    public boolean send(NotificationEntity entity, String emailTo) {
        NotificationDao dao = new NotificationDao();

        // Need to check if the contract is already notified before
        // Unique constraint: type + extra data
        // Check types: 4.1, 4.2, 4.3
        int type41 = NotificationEntity.Type.CONTRACT_NEARLY_EXPIRED_1;
        int type42 = NotificationEntity.Type.CONTRACT_NEARLY_EXPIRED_2;
        int type43 = NotificationEntity.Type.CONTRACT_NEARLY_EXPIRED_3;

        if (entity.getType() == type41 ||
                entity.getType() == type42 ||
                entity.getType() == type43) {

            NotificationEntity existsNotif = dao.isNotified(entity.getType(),
                    entity.getExtraData());

            if (existsNotif != null) {
                // No need to do anything;
                return true;
            }
        }
        // create the notification
        NotificationEntity newNotifEntity = dao.create(entity);


        // If the notification method is EMAIL
        if (emailTo != null &&
                (entity.getMethod() & NotificationEntity.Method.EMAIL) == entity.getMethod()) {

            String content = EmailTemplate.NOTIFY_EMAIL;
            if (content != null) {
                content = content
                        .replaceAll("\\{\\{content\\}\\}", entity.getContent())
                        .replaceAll("\\{\\{link\\}\\}", entity.generateRelatedLink("customer")); // TODO: where is the generated role?
                EmailUtils.sendMail(emailTo, entity.getContent(), content);
            }
        }

        return newNotifEntity != null;

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

    public NotificationDto get(int id) {
        NotificationDao dao = new NotificationDao();
        return dao.get(id);
    }
}
