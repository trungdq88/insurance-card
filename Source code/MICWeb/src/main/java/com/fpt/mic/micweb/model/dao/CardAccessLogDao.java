package com.fpt.mic.micweb.model.dao;

import com.fpt.mic.micweb.model.dao.common.GenericDaoJpaImpl;
import com.fpt.mic.micweb.model.entity.CardAccessLogEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by Kha on 05/07/2015.
 */
public class CardAccessLogDao extends GenericDaoJpaImpl<CardAccessLogEntity, Integer> {
    public List<CardAccessLogEntity> getCardAccessLog(int cardId, int offset, int count) {
        EntityManager entityManager = factory.createEntityManager();
        String hql = "SELECT cal FROM CardAccessLogEntity AS cal " +
                "WHERE cal.cardInstanceId = :id " +
                "ORDER BY cal.accessDate DESC";
        Query query = entityManager.createQuery(hql);
        query.setParameter("id", cardId);
        query.setFirstResult(offset);
        query.setMaxResults(count);
        List<CardAccessLogEntity> resultList = query.getResultList();
        entityManager.close();
        return resultList;
    }

    public Long getCardAccessLogCount(int cardId) {
        EntityManager entityManager = factory.createEntityManager();
        String hql = "SELECT COUNT(cal) FROM CardAccessLogEntity AS cal " +
                "WHERE cal.cardInstanceId = :id " +
                "ORDER BY cal.accessDate DESC";
        Query query = entityManager.createQuery(hql);
        query.setParameter("id", cardId);
        Long result = (Long) query.getSingleResult();
        entityManager.close();
        return result;
    }

    public List getCardAccessLog(int cardInstanceId,
                                 Date filterBegin,
                                 Date filterEnd,
                                 int offset, int count) {
        EntityManager entityManager = factory.createEntityManager();
        String hql = "SELECT cal FROM CardAccessLogEntity AS cal " +
                "WHERE cal.cardInstanceId = :id " +
                "AND cal.accessDate BETWEEN :filter_begin AND :filter_end " +
                "ORDER BY cal.accessDate DESC";
        Query query = entityManager.createQuery(hql);
        query.setParameter("id", cardInstanceId);
        query.setParameter("filter_begin", filterBegin);
        query.setParameter("filter_end", filterEnd);
        query.setFirstResult(offset);
        query.setMaxResults(count);
        List resultList = query.getResultList();
        entityManager.close();
        return resultList;
    }

    public Long getCardAccessLogCount(int cardInstanceId,
                                      Date filterBegin,
                                      Date filterEnd) {
        EntityManager entityManager = factory.createEntityManager();
        String hql = "SELECT COUNT(cal) FROM CardAccessLogEntity AS cal " +
                "WHERE cal.cardInstanceId = :id " +
                "AND cal.accessDate BETWEEN :filter_begin AND :filter_end " +
                "ORDER BY cal.accessDate DESC";
        Query query = entityManager.createQuery(hql);
        query.setParameter("id", cardInstanceId);
        query.setParameter("filter_begin", filterBegin);
        query.setParameter("filter_end", filterEnd);
        Long result = (Long) query.getSingleResult();
        entityManager.close();
        return result;
    }
}
