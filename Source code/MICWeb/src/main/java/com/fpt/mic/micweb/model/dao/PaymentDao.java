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
    public List<PaymentEntity> getPaymentByCustomerCode(String contractCode) {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT pm FROM PaymentEntity AS pm " +
                "WHERE pm.contractCode = :code";
        Query query = entity.createQuery(hql);
        query.setParameter("code", contractCode);
        return query.getResultList();
    }
}
