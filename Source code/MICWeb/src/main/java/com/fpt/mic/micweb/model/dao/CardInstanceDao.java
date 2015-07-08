package com.fpt.mic.micweb.model.dao;

import com.fpt.mic.micweb.model.dao.common.GenericDaoJpaImpl;
import com.fpt.mic.micweb.model.entity.CardInstanceEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by dinhquangtrung on 6/2/15.
 */
public class CardInstanceDao extends GenericDaoJpaImpl<CardInstanceEntity, Integer> {

    public List<CardInstanceEntity> getIssuedCard(int offset, int count) {
        EntityManager entityManager = factory.createEntityManager();
        String hql = "SELECT ca FROM CardInstanceEntity AS ca " +
                "ORDER BY ca.deactivatedDate, ca.activatedDate DESC";
        Query query = entityManager.createQuery(hql);
        query.setFirstResult(offset);
        query.setMaxResults(count);
        List<CardInstanceEntity> resultList = query.getResultList();
        entityManager.close();
        return resultList;
    }

    public Long getIssuedCardCount() {
        EntityManager entityManager = factory.createEntityManager();
        String hql = "SELECT COUNT(ca) FROM CardInstanceEntity AS ca " +
                "ORDER BY ca.deactivatedDate, ca.activatedDate DESC";
        Query query = entityManager.createQuery(hql);
        Long singleResult = (Long) query.getSingleResult();
        entityManager.close();
        return singleResult;
    }

    public List<CardInstanceEntity> getAllCard() {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT ca FROM CardInstanceEntity ca";
        Query query = entity.createQuery(hql);
        return query.getResultList();
    }

    public CardInstanceEntity getActiveCardInstanceByContract(String contractCode) {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT ca FROM CardInstanceEntity ca WHERE ca.contractCode = :contractCode " +
                "AND ca.deactivatedDate = NULL";
        Query query = entity.createQuery(hql);
        query.setParameter("contractCode", contractCode);
        try {
            return (CardInstanceEntity) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<CardInstanceEntity> getCardInstancesByContractIncludeDeactive(String contractCode) {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT ca FROM CardInstanceEntity ca WHERE ca.contractCode = :contractCode ";
        Query query = entity.createQuery(hql);
        query.setParameter("contractCode", contractCode);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Check card status
     * If card is deactivated, returns null
     *
     * @param cardID
     * @return
     */
    public CardInstanceEntity checkCard(String cardID) {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT ca FROM CardInstanceEntity ca WHERE ca.cardId = :cardID " +
                "AND ca.deactivatedDate = NULL";
        Query query = entity.createQuery(hql);
        query.setParameter("cardID", cardID);
        try {
            return (CardInstanceEntity) query.getSingleResult();
        } catch (NonUniqueResultException e) {
            e.printStackTrace();
            System.out.println("checkCard NonUniqueResultException: " +
                    "possibility database inconsistency due to handy modification");
            return null;
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Check if a card ID is exists in the system (both activated and deactivated)
     * @param cardID
     * @return
     */
    public List getCardInstancesByCardID(String cardID) {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT ca FROM CardInstanceEntity ca WHERE ca.cardId = :cardID";
        Query query = entity.createQuery(hql);
        query.setParameter("cardID", cardID);
        return query.getResultList();
    }
}
