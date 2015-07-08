package com.fpt.mic.micweb.model.dao;

import com.fpt.mic.micweb.model.dao.common.GenericDaoJpaImpl;
import com.fpt.mic.micweb.model.entity.CardAccessLogEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Kha on 05/07/2015.
 */
public class CardAccessLogDao extends GenericDaoJpaImpl<CardAccessLogEntity, Integer> {
    public List<CardAccessLogEntity> getCardAccessLog(String cardId, int offset, int count) {
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

    public Long getCardAccessLogCount(String cardId) {
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
}
