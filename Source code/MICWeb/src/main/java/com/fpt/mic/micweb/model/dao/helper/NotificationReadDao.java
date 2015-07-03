package com.fpt.mic.micweb.model.dao.helper;

import com.fpt.mic.micweb.model.dao.common.GenericDaoJpaImpl;
import com.fpt.mic.micweb.model.entity.helper.NotificationReadEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 7/3/15.
 */
public class NotificationReadDao extends GenericDaoJpaImpl<NotificationReadEntity, Integer> {

    public NotificationReadEntity get(int id, String userCode) {
        EntityManager entityManager = factory.createEntityManager();

        Query query = entityManager.createQuery(
                "SELECT nr FROM NotificationReadEntity nr " +
                        "WHERE nr.notificationId = :id AND nr.userCode = :user_code");
        query.setParameter("id", id);
        query.setParameter("user_code", userCode);
        query.setMaxResults(1);
        NotificationReadEntity entity = null;
        try {
            entity = (NotificationReadEntity) query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        entityManager.close();
        return entity;
    }
}
