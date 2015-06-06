package com.fpt.mic.micweb.model.dao;

import com.fpt.mic.micweb.model.entity.ContractEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by PhucNguyen on 06/05/2015.
 */
public class ContractDao extends GenericDaoJpaImpl<ContractEntity, String> {
    public List<ContractEntity> getListContract() {
        EntityManager manager = factory.createEntityManager();
        Query query = manager.createQuery("SELECT c FROM ContractEntity c");
        return query.getResultList();
    }
}
