package com.fpt.mic.micweb.model.dao;

import com.fpt.mic.micweb.model.dao.common.IncrementDao;
import com.fpt.mic.micweb.model.dto.ContractSearchResult;
import com.fpt.mic.micweb.model.entity.ContractEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by TriPQMSE60746 on 06/04/2015.
 */
public class ContractDao extends IncrementDao<ContractEntity, String> {
    public List<ContractEntity> getListContract() {
        EntityManager manager = factory.createEntityManager();
        Query query = manager.createQuery("SELECT c FROM ContractEntity c");
        return query.getResultList();
    }

    @Override
    protected String getCodePrefix() {
        return "HD";
    }

    /**
     * Search for contracts by contract code or customer name of the contract
     * @param keyword
     * @return
     */
    public List<ContractSearchResult> searchContracts(String keyword) {

        EntityManager entityManager = factory.createEntityManager();
        TypedQuery<ContractSearchResult> query = entityManager.createQuery(
                "SELECT NEW ContractSearchResult(co, cu) " +
                        "FROM ContractEntity co " +
                        "JOIN co.micCustomerByCustomerCode cu " +
                        "WHERE co.customerCode = cu.customerCode " +
                        "AND (co.contractCode LIKE :keyword OR cu.name LIKE :keyword)",
                ContractSearchResult.class
        );
        query.setParameter("keyword", "%" + keyword + "%");
        List<ContractSearchResult> resultList = query.getResultList();
        entityManager.close();
        return resultList;
    }

}
