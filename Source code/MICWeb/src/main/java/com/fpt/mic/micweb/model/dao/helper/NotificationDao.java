package com.fpt.mic.micweb.model.dao.helper;

import com.fpt.mic.micweb.model.dao.common.GenericDaoJpaImpl;
import com.fpt.mic.micweb.model.dto.NotificationDto;
import com.fpt.mic.micweb.model.entity.helper.NotificationEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 7/3/15.
 */
public class NotificationDao extends GenericDaoJpaImpl<NotificationEntity, Integer> {
    public List getAllNotifications(String code) {
        EntityManager entityManager = factory.createEntityManager();

        Query query = entityManager.createNativeQuery(
                "SELECT n.*, nr.is_read " +
                        "FROM mic_notification n " +
                        "LEFT JOIN mic_notification_read nr " +
                        "ON n.id = nr.notification_id " +
                        "WHERE :user_code REGEXP receiver " +
                        "ORDER BY n.created_date DESC ",
                NotificationDto.class);
        query.setParameter("user_code", code);
        List resultList = query.getResultList();
        entityManager.close();
        return resultList;
    }

    public List getUnreadNotifications(String code) {
        return getUnreadNotifications(code, 0);
    }

    public List getUnreadNotifications(String code, int size) {

        EntityManager entityManager = factory.createEntityManager();

        Query query = entityManager.createNativeQuery(
                "SELECT n.*, nr.is_read " +
                        "FROM mic_notification n " +
                        "LEFT JOIN mic_notification_read nr " +
                        "ON n.id = nr.notification_id " +
                        "WHERE :user_code REGEXP receiver " +
                        "AND nr.is_read IS NULL " +
                        "ORDER BY n.created_date DESC ",
                NotificationDto.class);
        query.setParameter("user_code", code);
        if (size > 0) {
            query.setMaxResults(size);
        }
        List resultList = query.getResultList();
        entityManager.close();
        return resultList;
    }

    public BigInteger getUnreadNotificationsCount(String code) {
        EntityManager entityManager = factory.createEntityManager();

        Query query = entityManager.createNativeQuery(
                "SELECT COUNT(n.id) AS unread_count " +
                        "FROM mic_notification n " +
                        "LEFT JOIN mic_notification_read nr " +
                        "ON n.id = nr.notification_id " +
                        "WHERE :user_code REGEXP receiver " +
                        "AND nr.is_read IS NULL ");
        query.setParameter("user_code", code);
        BigInteger result = (BigInteger) query.getSingleResult();
        entityManager.close();
        return result;
    }

    public NotificationDto get(int id) {
        EntityManager entityManager = factory.createEntityManager();

        Query query = entityManager.createNativeQuery(
                "SELECT n.*, nr.is_read " +
                        "FROM mic_notification n " +
                        "LEFT JOIN mic_notification_read nr " +
                        "ON n.id = nr.notification_id " +
                        "WHERE n.id = :id " +
                        "ORDER BY n.created_date DESC ",
                NotificationDto.class);
        query.setParameter("id", id);
        NotificationDto resultList = null;
        try {
            resultList = (NotificationDto) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        entityManager.close();
        return resultList;
    }
}
