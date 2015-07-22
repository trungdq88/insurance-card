package com.fpt.mic.micweb.model.dao;

import com.fpt.mic.micweb.model.dao.common.GenericDaoJpaImpl;
import com.fpt.mic.micweb.model.entity.ContractTypeEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TriPQMSE60746 on 06/08/2015.
 */
public class ContractTypeDao extends GenericDaoJpaImpl<ContractTypeEntity, Integer> {

    /**
     * This is the method which get all payment belongs to the customer.
     * @return list of ContractTypeEntity.
     *
     * @author KhaNC
     * @version 1.0
     */
    public List<ContractTypeEntity> getAllContractType() {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT ct FROM ContractTypeEntity ct";
        Query query = entity.createQuery(hql);
        List<ContractTypeEntity> resultList = query.getResultList();
        entity.close();
        return resultList;
    }

    public List<ContractTypeEntity> getAllActiveContractType() {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT ct FROM ContractTypeEntity ct WHERE ct.active = 1";
        Query query = entity.createQuery(hql);
        return query.getResultList();
    }

    public Long getAllContractTypeCount() {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT COUNT(ct) FROM ContractTypeEntity ct";
        Query query = entity.createQuery(hql);
        Long result = (Long)query.getSingleResult();
        entity.close();
        return result;
    }

    public List getOnePageContractTypes(int offset, int count) {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT ct FROM ContractTypeEntity ct";
        Query query = entity.createQuery(hql);
        query.setFirstResult(offset);
        query.setMaxResults(count);
        List resultList = query.getResultList();
        entity.close();
        return resultList;
    }

    public Long getCountContractByContractTypeId(int contractTypeId) {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT count (ce) FROM ContractEntity ce " +
                "WHERE ce.contractTypeId = :contractTypeId";
        Query query = entity.createQuery(hql);
        query.setParameter("contractTypeId", contractTypeId);
        Long result =(Long) query.getSingleResult();
        entity.close();
        return result;
    }
}
