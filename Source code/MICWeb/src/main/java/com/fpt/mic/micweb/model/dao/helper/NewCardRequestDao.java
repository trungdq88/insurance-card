package com.fpt.mic.micweb.model.dao.helper;

import com.fpt.mic.micweb.model.dao.common.GenericDaoJpaImpl;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.model.entity.NewCardRequestEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by TriPQM on 07/03/2015.
 */
public class NewCardRequestDao extends GenericDaoJpaImpl<NewCardRequestEntity, Integer> {
    public NewCardRequestEntity getUnsolvedRequestByCardId(String cardId) {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT ca FROM NewCardRequestEntity ca WHERE ca.oldCardId = :oldCardId " +
                "AND ca.resolveDate = NULL";
        Query query = entity.createQuery(hql);
        query.setParameter("oldCardId", cardId);
        try {
            return (NewCardRequestEntity) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List getOnePageNewCardRequest(String customerCode, int offset, int count) {
        EntityManager entityManager = factory.createEntityManager();
        Query query = entityManager.createQuery(
                "SELECT co " +
                        "FROM NewCardRequestEntity co " +
                        "WHERE co.micCardByOldCardId.micContractByContractCode" +
                        ".micCustomerByCustomerCode.customerCode = :customerCode ORDER BY co.resolveDate ASC"
        );
        query.setParameter("customerCode", customerCode);
        query.setFirstResult(offset);
        query.setMaxResults(count);
        List resultList = query.getResultList();
        entityManager.close();
        return resultList;
    }

    public List getAllNewCardRequest() {
        EntityManager entityManager = factory.createEntityManager();
        Query query = entityManager.createQuery(
                "SELECT co " +
                        "FROM NewCardRequestEntity co "
        );
        List resultList = query.getResultList();
        entityManager.close();
        return resultList;
    }

    public NewCardRequestEntity getUnresolveRequestByContractCode(String contractCode) {
        EntityManager entityManager = factory.createEntityManager();
        Query query = entityManager.createQuery(
                "SELECT co " +
                        "FROM NewCardRequestEntity co " +
                        "WHERE co.micCardByOldCardId.micContractByContractCode.contractCode = :contractCode" +
                        " AND co.resolveDate = NULL"
        );
        query.setParameter("contractCode", contractCode);

        try {
            return (NewCardRequestEntity) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            entityManager.close();
        }
    }
    public NewCardRequestEntity getUnpaidRequestByContractCode(String contractCode) {
        EntityManager entityManager = factory.createEntityManager();
        Query query = entityManager.createQuery(
                "SELECT co " +
                        "FROM NewCardRequestEntity co " +
                        "WHERE co.micCardByOldCardId.micContractByContractCode.contractCode = :contractCode" +
                        " AND co.isPaid = 0"
        );
        query.setParameter("contractCode", contractCode);

        try {
            return (NewCardRequestEntity) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    public Long getAllNewCardRequestCount() {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT COUNT(co) FROM NewCardRequestEntity co ORDER BY co.id DESC";
        Query query = entity.createQuery(hql);
        Long result = (Long) query.getSingleResult();
        entity.close();
        return result;
    }

    public Long getAllNewCardRequestCount(String customerCode) {
        EntityManager entityManager = factory.createEntityManager();
        Query query = entityManager.createQuery(
                "SELECT COUNT (co) " +
                        "FROM NewCardRequestEntity co " +
                        "WHERE co.micCardByOldCardId.micContractByContractCode" +
                        ".micCustomerByCustomerCode.customerCode = :customerCode ORDER BY co.resolveDate ASC"
        );
        query.setParameter("customerCode", customerCode);
        Long result = (Long) query.getSingleResult();
        entityManager.close();
        return result;
    }

    public Long getUnresolvedNewCardRequestCount(String customerCode) {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT COUNT (co) " +
                "FROM NewCardRequestEntity co " +
                "WHERE co.micCardByOldCardId.micContractByContractCode" +
                ".micCustomerByCustomerCode.customerCode = :customerCode AND co.resolveDate = NULL ORDER BY co.resolveDate ASC";

        Query query = entity.createQuery(hql);
        query.setParameter("customerCode", customerCode);
        Long result = (Long) query.getSingleResult();
        entity.close();
        return result;
    }
}
