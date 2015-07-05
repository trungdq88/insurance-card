package com.fpt.mic.micweb.model.dao;

import com.fpt.mic.micweb.model.dao.common.GenericDaoJpaImpl;
import com.fpt.mic.micweb.model.entity.PunishmentEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by PhucNguyen on 04/07/2015.
 */
public class PunishmentDao extends GenericDaoJpaImpl<PunishmentEntity, Integer> {
    public List<PunishmentEntity> getAllPunishmentByContractCode(String contractCode) {
        EntityManager entityManager = factory.createEntityManager();
        String hql = "SELECT p FROM PunishmentEntity AS p " +
                "WHERE p.contractCode = :code " +
                "ORDER BY p.id DESC";
        Query query = entityManager.createQuery(hql);
        query.setParameter("code", contractCode);
        List<PunishmentEntity> resultList = query.getResultList();
        entityManager.close();
        return resultList;
    }

    public Long getAllPunishmentByContractCodeCount(String contractCode) {
        EntityManager entityManager = factory.createEntityManager();
        String hql = "SELECT COUNT(punishment) FROM PunishmentEntity AS punishment " +
                "WHERE punishment.contractCode = :code " +
                "ORDER BY punishment.createdDate DESC";
        Query query = entityManager.createQuery(hql);
        query.setParameter("code", contractCode);
        Long singleResult = (Long) query.getSingleResult();
        entityManager.close();
        return singleResult;
    }

    public List getPunishmentByContractCode(String contractCode, int offset, int count) {
        EntityManager entityManager = factory.createEntityManager();
        String hql = "SELECT punishment FROM PunishmentEntity AS punishment " +
                "WHERE punishment.contractCode = :code " +
                "ORDER BY punishment.createdDate DESC";
        Query query = entityManager.createQuery(hql);
        query.setParameter("code", contractCode);
        query.setFirstResult(offset);
        query.setMaxResults(count);
        List resultList = query.getResultList();
        entityManager.close();
        return resultList;
    }

}
