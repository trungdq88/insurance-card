package com.fpt.mic.micweb.model.dao;

import com.fpt.mic.micweb.model.dao.common.GenericDaoJpaImpl;
import com.fpt.mic.micweb.model.entity.CardEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by dinhquangtrung on 6/2/15.
 */
public class CardDao extends GenericDaoJpaImpl<CardEntity, String> {

    public List<CardEntity> getIssuedCard(int offset, int count) {
        EntityManager entityManager = factory.createEntityManager();
        String hql = "SELECT ca FROM CardEntity AS ca " +
                "ORDER BY ca.deactivatedDate, ca.activatedDate DESC";
        Query query = entityManager.createQuery(hql);
        query.setFirstResult(offset);
        query.setMaxResults(count);
        List<CardEntity> resultList = query.getResultList();
        entityManager.close();
        return resultList;
    }
    public List<CardEntity> getIssuedCard(String customerCode, int offset, int count) {
        EntityManager entityManager = factory.createEntityManager();
        String hql = "SELECT ca FROM CardEntity AS ca " +
                "WHERE ca.micContractByContractCode.micCustomerByCustomerCode.customerCode = :customerCode" +
                " ORDER BY ca.deactivatedDate, ca.activatedDate DESC";
        Query query = entityManager.createQuery(hql);
        query.setParameter("customerCode",customerCode);
        query.setFirstResult(offset);
        query.setMaxResults(count);
        List<CardEntity> resultList = query.getResultList();
        entityManager.close();
        return resultList;
    }

    public Long getIssuedCardCount() {
        EntityManager entityManager = factory.createEntityManager();
        String hql = "SELECT COUNT(ca) FROM CardEntity AS ca " +
                "ORDER BY ca.deactivatedDate, ca.activatedDate DESC";
        Query query = entityManager.createQuery(hql);
        Long singleResult = (Long) query.getSingleResult();
        entityManager.close();
        return singleResult;
    }

    public Long getIssuedCardCount(String customerCode) {
        EntityManager entityManager = factory.createEntityManager();
        String hql = "SELECT COUNT(ca) FROM CardEntity AS ca " +
                "WHERE ca.micContractByContractCode.micCustomerByCustomerCode.customerCode = :customerCode" +
                " ORDER BY ca.deactivatedDate, ca.activatedDate DESC";
        Query query = entityManager.createQuery(hql);
        query.setParameter("customerCode",customerCode);
        Long singleResult = (Long) query.getSingleResult();
        entityManager.close();
        return singleResult;
    }

    public List<CardEntity> getAllCard() {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT ca FROM CardEntity ca";
        Query query = entity.createQuery(hql);
        return query.getResultList();
    }

    public CardEntity getCardByContract(String contractCode) {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT ca FROM CardEntity ca WHERE ca.contractCode = :contractCode " +
                "AND ca.deactivatedDate = NULL";
        Query query = entity.createQuery(hql);
        query.setParameter("contractCode", contractCode);
        try {
            return (CardEntity) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<CardEntity> getCardByContractIncludeDeactive(String contractCode) {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT ca FROM CardEntity ca WHERE ca.contractCode = :contractCode ";
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
    public CardEntity checkCard(String cardID) {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT ca FROM CardEntity ca WHERE ca.cardId = :cardID " +
                "AND ca.deactivatedDate = NULL";
        Query query = entity.createQuery(hql);
        query.setParameter("cardID", cardID);
        try {
            return (CardEntity) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
