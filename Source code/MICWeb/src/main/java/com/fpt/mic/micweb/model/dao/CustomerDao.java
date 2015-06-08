package com.fpt.mic.micweb.model.dao;

import com.fpt.mic.micweb.model.dao.common.IncrementDao;
import com.fpt.mic.micweb.model.dto.CustomerDTO;
import com.fpt.mic.micweb.model.entity.CustomerEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by TriPQMSE60746 on 06/05/2015.
 */
public class CustomerDao extends IncrementDao<CustomerEntity, String> {
    /*public List<CustomerEntity> getAllCustomer() {
        CustomerEntity lstCustomer = new CustomerEntity();
        EntityManager entity = factory.createEntityManager();
        Query query = entity.createQuery("SELECT * FROM mic_customer ");
        return query.getResultList();
    }*/

    /* New function created by Kha */
    public List<CustomerEntity> getAllCustomer() {
        EntityManager entity = factory.createEntityManager();
        /*String hql = "SELECT CU.customerCode, CU.name, CU.phone, CO.contractCode, CA.cardId " +
                "FROM CustomerEntity AS CU, ContractEntity AS CO, CardEntity AS CA " +
                "WHERE CU.customerCode = CO.customerCode " +
                "AND CO.contractCode = CA.contractCode";*/
        String hql = "FROM CustomerEntity";
        Query query = entity.createQuery(hql);
        return query.getResultList();
    }

    @Override
    protected String getCodePrefix() {
        return "KH";
    }
}
