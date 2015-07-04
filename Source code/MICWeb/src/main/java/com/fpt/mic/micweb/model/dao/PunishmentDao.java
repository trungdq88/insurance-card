package com.fpt.mic.micweb.model.dao;

import com.fpt.mic.micweb.model.dao.common.IncrementDao;
import com.fpt.mic.micweb.model.entity.PunishmentEntity;
import com.fpt.mic.micweb.utils.Constants;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

import javax.persistence.TypedQuery;

/**
 * Created by PhucNguyen on 04/07/2015.
 */
public class PunishmentDao extends IncrementDao<PunishmentEntity, String> {
    @Override
    protected String getCodePrefix() {
        return "BT";
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
