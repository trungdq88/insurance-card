package com.fpt.mic.micweb.model.dao;

import com.fpt.mic.micweb.model.dao.common.GenericDaoJpaImpl;
import com.fpt.mic.micweb.model.entity.CardEntity;
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

    /**
     * Return list of CardEntity with all the instance assigned with the card,
     * the card instances are order by activated date
     * @param offset
     * @param count
     * @return
     */
    public List<CardEntity> getIssuedCard(int offset, int count) {
        EntityManager entityManager = factory.createEntityManager();
        String hql = "SELECT c FROM CardEntity c " +
                "JOIN FETCH c.micCardInstancesByCardId AS cardInstances " +
                "ORDER BY cardInstances.activatedDate DESC";
        Query query = entityManager.createQuery(hql);
        query.setFirstResult(offset);
        query.setMaxResults(count);
        List<CardEntity> resultList = query.getResultList();
        entityManager.close();
        return resultList;
    }

    /**
     * Return all card instances of the contract of the customer
     * @param customerCode
     * @param offset
     * @param count
     * @return
     */
    public List<CardInstanceEntity> getIssuedCard(String customerCode, int offset, int count) {
        EntityManager entityManager = factory.createEntityManager();
        String hql = "SELECT ci FROM CardInstanceEntity ci " +
                "WHERE ci.micContractByContractCode.customerCode = :customerCode " +
                "AND ci.deactivatedDate = NULL";
        Query query = entityManager.createQuery(hql);
        query.setParameter("customerCode",customerCode);
        query.setFirstResult(offset);
        query.setMaxResults(count);
        List<CardInstanceEntity> resultList = query.getResultList();
        entityManager.close();
        return resultList;
    }
    public Long getIssuedCardCount(String customerCode) {
        EntityManager entityManager = factory.createEntityManager();
        String hql = "SELECT COUNT(ci) FROM CardInstanceEntity ci " +
                "WHERE ci.micContractByContractCode.customerCode = :customerCode " +
                "AND ci.deactivatedDate = NULL";
        Query query = entityManager.createQuery(hql);
        query.setParameter("customerCode", customerCode);
        Long singleResult = (Long) query.getSingleResult();
        entityManager.close();
        return singleResult;
    }
    public Long getIssuedCardCount() {
        EntityManager entityManager = factory.createEntityManager();
        String hql = "SELECT COUNT(c) FROM CardEntity c";
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
        } catch (NonUniqueResultException e) {
            e.printStackTrace();
            System.out.println("checkCard NonUniqueResultException: " +
                    "possibility database inconsistency due to handy modification");
            return null;
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<CardInstanceEntity> getCardInstancesByContractIncludeDeactive(String contractCode) {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT ca FROM CardInstanceEntity AS ca WHERE ca.contractCode = :contractCode " +
                "ORDER BY ca.activatedDate DESC";
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

    public List<CardInstanceEntity> getAllCardInstancesByCardID(String cardID) {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT ca FROM CardInstanceEntity AS ca WHERE ca.cardId = :cardID " +
                "ORDER BY ca.activatedDate DESC";
        Query query = entity.createQuery(hql);
        query.setParameter("cardID", cardID);
        List<CardInstanceEntity> resultList = query.getResultList();
        entity.close();
        return resultList;
    }

    public CardInstanceEntity getLastActiveCardInstanceByCardId(String cardId) {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT ca FROM CardInstanceEntity ca WHERE ca.cardId = :cardId " +
                " ORDER BY ca.activatedDate DESC";
        Query query = entity.createQuery(hql);
        query.setMaxResults(1);
        query.setParameter("cardId", cardId);
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
}
