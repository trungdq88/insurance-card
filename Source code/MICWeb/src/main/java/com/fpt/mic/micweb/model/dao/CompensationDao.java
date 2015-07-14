package com.fpt.mic.micweb.model.dao;

import com.fpt.mic.micweb.model.dao.common.IncrementDao;
import com.fpt.mic.micweb.model.entity.CompensationEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by KhaNC on 03/07/2015.
 */
public class CompensationDao extends IncrementDao<CompensationEntity, String> {
    @Override
    protected String getCodePrefix() {
        return "BT";
    }

    public List<CompensationEntity> getAllCompensation(int offset, int count) {
        EntityManager entityManager = factory.createEntityManager();
        String hql = "SELECT cp FROM CompensationEntity AS cp " +
                "ORDER BY cp.resolveDate, cp.createdDate";
        Query query = entityManager.createQuery(hql);
        query.setFirstResult(offset);
        query.setMaxResults(count);
        List<CompensationEntity> resultList = query.getResultList();
        entityManager.close();
        return resultList;
    }

    public Long getAllCompensationCount() {
        EntityManager entityManager = factory.createEntityManager();
        String hql = "SELECT COUNT(cp) FROM CompensationEntity AS cp " +
                "ORDER BY cp.compensationCode DESC";
        Query query = entityManager.createQuery(hql);
        Long singleResult = (Long) query.getSingleResult();
        entityManager.close();
        return singleResult;
    }

    public Long getAllUnresolvedCompensationCount() {
        EntityManager entityManager = factory.createEntityManager();
        String hql = "SELECT COUNT(cp) FROM CompensationEntity AS cp " +
                "WHERE cp.resolveDate = NULL";
        Query query = entityManager.createQuery(hql);
        Long singleResult = (Long) query.getSingleResult();
        entityManager.close();
        return singleResult;
    }

    public List<CompensationEntity> getCompensationByContractCode(String contractCode) {
        EntityManager entityManager = factory.createEntityManager();
        String hql = "SELECT cp FROM CompensationEntity AS cp " +
                "WHERE cp.contractCode = :code " +
                "ORDER BY cp.resolveDate, cp.createdDate";
        Query query = entityManager.createQuery(hql);
        query.setParameter("code", contractCode);
        List<CompensationEntity> resultList = query.getResultList();
        entityManager.close();
        return resultList;
    }

    public Long getAllCompensationByContractCodeCount(String contractCode) {
        EntityManager entityManager = factory.createEntityManager();
        String hql = "SELECT COUNT(compensation) FROM CompensationEntity AS compensation " +
                "WHERE compensation.contractCode = :code " +
                "ORDER BY compensation.compensationCode DESC";
        Query query = entityManager.createQuery(hql);
        query.setParameter("code", contractCode);
        Long singleResult = (Long) query.getSingleResult();
        entityManager.close();
        return singleResult;
    }

    public List getCompensationByContractCode(String contractCode, int offset, int count) {
        EntityManager entityManager = factory.createEntityManager();
        String hql = "SELECT compensation FROM CompensationEntity AS compensation " +
                "WHERE compensation.contractCode = :code " +
                "ORDER BY compensation.compensationCode DESC";
        Query query = entityManager.createQuery(hql);
        query.setParameter("code", contractCode);
        query.setFirstResult(offset);
        query.setMaxResults(count);
        List resultList = query.getResultList();
        entityManager.close();
        return resultList;
    }
}
