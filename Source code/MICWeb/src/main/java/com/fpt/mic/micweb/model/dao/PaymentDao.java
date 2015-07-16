package com.fpt.mic.micweb.model.dao;

import com.fpt.mic.micweb.model.dao.common.GenericDaoJpaImpl;
import com.fpt.mic.micweb.model.entity.PaymentEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by TriPQMSE60746 on 06/05/2015.
 */
public class PaymentDao extends GenericDaoJpaImpl<PaymentEntity, Integer> {

    /**
     * This is the method which get all payment belongs to the customer.
     * @param contractCode
     * @return list of PaymentEntity.
     *
     * @author KhaNC
     * @version 1.0
     */
    public List<PaymentEntity> getPaymentByContractCode(String contractCode) {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT pm FROM PaymentEntity AS pm " +
                "WHERE pm.contractCode = :code";
        Query query = entity.createQuery(hql);
        query.setParameter("code", contractCode);
        return query.getResultList();
    }
    public boolean isPaidContract(String contractCode){
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT pm FROM PaymentEntity AS pm " +
                "WHERE pm.contractCode = :code";
        Query query = entity.createQuery(hql);
        query.setParameter("code", contractCode);
        List result = query.getResultList();
        if (result.size() == 0) {
            return false;
        }
            return true;
    }
    public List<PaymentEntity> getAllPaymentByCustomerCode(String customerCode) {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT pm FROM PaymentEntity AS pm " +
                "inner join pm.micContractByContractCode c " +
                "WHERE pm.contractCode = c.contractCode " +
                "AND c.customerCode = :customerCode " ;
        Query query = entity.createQuery(hql);
        query.setParameter("customerCode", customerCode);
        return query.getResultList();
    }

    public Long getAllPaymentByContractCodeCount(String customerCode) {
        EntityManager entityManager = factory.createEntityManager();
        String hql = "SELECT pm FROM PaymentEntity AS pm " +
                "inner join pm.micContractByContractCode c " +
                "WHERE pm.contractCode = c.contractCode " +
                "AND c.customerCode = :customerCode " ;
        Query query = entityManager.createQuery(hql);
        query.setParameter("customerCode", customerCode);
        Long singleResult = (Long) query.getSingleResult();
        entityManager.close();
        return singleResult;
    }

    public List getPaymentByCustomerCode(String customerCode, int offset, int count) {
        EntityManager entityManager = factory.createEntityManager();
        String hql = "SELECT pm FROM PaymentEntity AS pm " +
                "inner join pm.micContractByContractCode c " +
                "WHERE pm.contractCode = c.contractCode " +
                "AND c.customerCode = :customerCode " ;
        Query query = entityManager.createQuery(hql);
        query.setParameter("customerCode", customerCode);
        query.setFirstResult(offset);
        query.setMaxResults(count);
        List resultList = query.getResultList();
        entityManager.close();
        return resultList;
    }
}
