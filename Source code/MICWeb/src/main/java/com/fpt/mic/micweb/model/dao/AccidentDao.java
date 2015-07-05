package com.fpt.mic.micweb.model.dao;

import com.fpt.mic.micweb.model.dao.common.GenericDaoJpaImpl;
import com.fpt.mic.micweb.model.entity.AccidentEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Kha on 05/07/2015.
 */
public class AccidentDao extends GenericDaoJpaImpl<AccidentEntity, Integer> {
    public List<AccidentEntity> getAllAccidentByContractCode(String contractCode) {
        EntityManager entityManager = factory.createEntityManager();
        String hql = "SELECT a FROM AccidentEntity AS a " +
                "WHERE a.contractCode = :code " +
                "ORDER BY a.createdDate DESC";
        Query query = entityManager.createQuery(hql);
        query.setParameter("code", contractCode);
        List<AccidentEntity> resultList = query.getResultList();
        entityManager.close();
        return resultList;
    }
}
