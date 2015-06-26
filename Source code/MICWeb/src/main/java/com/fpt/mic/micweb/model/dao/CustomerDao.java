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
    /*public List<CustomerEntity> getAllCustomer() {
        CustomerEntity lstCustomer = new CustomerEntity();
        EntityManager entity = factory.createEntityManager();
        Query query = entity.createQuery("SELECT * FROM mic_customer ");
        return query.getResultList();
    }*/

    /**
     * This is the method which get all customer.
     * Expected return to CustomerDTO, implement later
     *
     * @return list This is result of the query.
     * @author KhaNC
     * @version 1.0
     */
    public List<CustomerEntity> getAllCustomer() {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT c FROM CustomerEntity c ORDER BY c.customerCode DESC";
        Query query = entity.createQuery(hql);
        return query.getResultList();
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
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT cu FROM CustomerEntity AS cu " +
                "WHERE cu.email = :email";
        Query query = entity.createQuery(hql);
        query.setParameter("email", email);
        if (query.getResultList().size() == 0) {
            return false;
        }
        return true;
    }
}
