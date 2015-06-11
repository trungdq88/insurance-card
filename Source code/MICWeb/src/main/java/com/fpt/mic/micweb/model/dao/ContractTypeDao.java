package com.fpt.mic.micweb.model.dao;

import com.fpt.mic.micweb.model.dao.common.GenericDaoJpaImpl;
import com.fpt.mic.micweb.model.entity.ContractTypeEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by TriPQMSE60746 on 06/08/2015.
 */
public class ContractTypeDao extends GenericDaoJpaImpl<ContractTypeEntity, Integer> {

    /**
     * This is the method which get all payment belongs to the customer.
     * @return list of ContractTypeEntity.
     *
     * @author KhaNC
     * @version 1.0
     */
    public List<ContractTypeEntity> getAllContractType() {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT ct FROM ContractTypeEntity ct";
        Query query = entity.createQuery(hql);
        return query.getResultList();
    }
}
