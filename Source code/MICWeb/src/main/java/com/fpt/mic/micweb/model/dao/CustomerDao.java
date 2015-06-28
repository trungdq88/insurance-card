package com.fpt.mic.micweb.model.dao;

import com.fpt.mic.micweb.model.dao.common.IncrementDao;
import com.fpt.mic.micweb.model.entity.CustomerEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by TriPQM on 06/05/2015.
 */
public class CustomerDao extends IncrementDao<CustomerEntity, String> {
    /**
     * This is the method which get all customer.
     *
     * @return list This is result of the query.
     * @author KhaNC
     * @version 1.0
     * @param offset
     * @param count
     */
    public List getAllCustomer(int offset, int count) {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT c FROM CustomerEntity c ORDER BY c.customerCode DESC";
        Query query = entity.createQuery(hql);
        query.setFirstResult(offset);
        query.setMaxResults(count);
        List resultList = query.getResultList();
        entity.close();
        return resultList;
    }

    public Long getAllCustomerCount() {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT COUNT(c) FROM CustomerEntity c ORDER BY c.customerCode DESC";
        Query query = entity.createQuery(hql);
        Long singleResult = (Long) query.getSingleResult();
        entity.close();
        return singleResult;
    }

    @Override
    protected String getCodePrefix() {
        return "KH";
    }

    public CustomerEntity getCustomerByEmail(String email) {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT c FROM CustomerEntity c WHERE c.email = :email";
        Query query = entity.createQuery(hql);
        query.setParameter("email", email);
        try {
            return (CustomerEntity) query.getSingleResult();
        } catch (NonUniqueResultException e) {
            return null;
        } catch (NoResultException e) {
            return null;
        }
    }

    public boolean isExistByEmail(String email) {
        EntityManager entityManager = factory.createEntityManager();
        String hql = "SELECT c FROM CustomerEntity c WHERE c.email= :email";
        Query query = entityManager.createQuery(hql);
        query.setParameter("email", email);
        if (query.getResultList().size() == 0) {
            entityManager.close();
            return false;
        }
        entityManager.close();
        return true;
    }

    /**
     * Return customers with name or code that contains keyword
     * @param keyword
     * @param offset
     * @param count
     */
    public List searchCustomerByNameOrCode(String keyword, int offset, int count) {
        EntityManager entity = factory.createEntityManager();
        Query query = entity.createQuery(
                "SELECT c FROM CustomerEntity c WHERE c.customerCode LIKE :keyword " +
                        "OR c.name LIKE :keyword");
        query.setParameter("keyword", "%" + keyword + "%");
        query.setFirstResult(offset);
        query.setMaxResults(count);
        List resultList = query.getResultList();
        entity.close();
        return resultList;
    }

    public Long searchCustomerByNameOrCodeCount(String keyword) {
        EntityManager entity = factory.createEntityManager();
        Query query = entity.createQuery(
                "SELECT COUNT(c) FROM CustomerEntity c WHERE c.customerCode LIKE :keyword " +
                        "OR c.name LIKE :keyword");
        query.setParameter("keyword", "%" + keyword + "%");
        Long result = (Long) query.getSingleResult();
        entity.close();
        return result;
    }
}
