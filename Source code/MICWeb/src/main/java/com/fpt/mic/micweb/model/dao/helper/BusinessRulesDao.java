package com.fpt.mic.micweb.model.dao.helper;

import com.fpt.mic.micweb.model.dao.common.GenericDaoJpaImpl;
import com.fpt.mic.micweb.model.entity.helper.BusinessRulesEntity;
import com.fpt.mic.micweb.model.entity.helper.IncrementsEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by TriPQM on 07/05/2015.
 */
public class BusinessRulesDao extends GenericDaoJpaImpl<BusinessRulesEntity, Integer> {
    public List getOnePageBusinessRules(int offset, int count) {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT co FROM BusinessRulesEntity co ORDER BY co.startDate DESC";
        Query query = entity.createQuery(hql);
        query.setFirstResult(offset);
        query.setMaxResults(count);
        List resultList = query.getResultList();
        entity.close();
        return resultList;
    }

    public Long getAllBusinessRulesCount() {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT COUNT(co) FROM BusinessRulesEntity co";
        Query query = entity.createQuery(hql);
        Long result = (Long) query.getSingleResult();
        entity.close();
        return result;
    }

    public BusinessRulesEntity getLastActiveBusinessRule(){
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT b FROM BusinessRulesEntity b " +
                "WHERE b.startDate = (SELECT MAX(bb.startDate) FROM BusinessRulesEntity bb)";
        Query query = entity.createQuery(hql);
        BusinessRulesEntity rulesEntity = null;
        try {
            rulesEntity =(BusinessRulesEntity) query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        entity.close();
        return rulesEntity;
    }
}
