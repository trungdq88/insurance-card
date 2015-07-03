package com.fpt.mic.micweb.model.dao.helper;

import com.fpt.mic.micweb.model.dao.common.GenericDaoJpaImpl;
import com.fpt.mic.micweb.model.entity.NewCardRequestEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

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
}
