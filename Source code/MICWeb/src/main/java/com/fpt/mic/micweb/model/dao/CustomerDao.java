package com.fpt.mic.micweb.model.dao;

import com.fpt.mic.micweb.model.entity.CustomerEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by PhucNguyen on 05/06/2015.
 */
public class CustomerDao extends GenericDaoJpaImpl<CustomerEntity, String> {
    public List<CustomerEntity> getAllCustomer() {
        CustomerEntity lstCustomer = new CustomerEntity();
        EntityManager entity = factory.createEntityManager();
        Query query = entity.createQuery("SELECT * FROM mic_customer ");
        return query.getResultList();
    }

}
