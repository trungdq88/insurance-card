package com.fpt.mic.micweb.model.dao;

import com.fpt.mic.micweb.model.dao.common.IncrementDao;
import com.fpt.mic.micweb.model.dto.ContractSearchResultDto;
import com.fpt.mic.micweb.model.entity.ContractEntity;
import com.fpt.mic.micweb.utils.Constants;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

import javax.persistence.TypedQuery;

/**
 * Created by TriPQM on 06/04/2015.
 */
public class ContractDao extends IncrementDao<ContractEntity, String> {
    public List<ContractEntity> getListContract() {
        EntityManager manager = factory.createEntityManager();
        Query query = manager.createQuery("SELECT co FROM ContractEntity co");
        List resultList = query.getResultList();
        manager.close();
        return resultList;
    }

    /**
     * Return all contract count
     *
     * @return list of ContractEntity.
     * @author KhaNC
     * @version 1.0
     */
    public Long getAllContractCount() {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT COUNT(co) FROM ContractEntity co ORDER BY co.contractCode DESC";
        Query query = entity.createQuery(hql);
        Long result = (Long) query.getSingleResult();
        entity.close();
        return result;
    }

    /**
     * Get all contract with offset and count
     * @param offset
     * @param count
     * @return
     */
    public List getAllContract(int offset, int count) {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT co FROM ContractEntity co ORDER BY co.contractCode DESC";
        Query query = entity.createQuery(hql);
        query.setFirstResult(offset);
        query.setMaxResults(count);
        List resultList = query.getResultList();
        entity.close();
        return resultList;
    }
    /**
     * This is the method which get all contract belongs to the customer.
     *
     * @param custCode This is stand for customer's code.
     * @param offset
     *@param count @return list of ContractEntity.
     * @author KhaNC
     * @version 1.0
     */
    public List getContractByCustomerCode(String custCode, int offset, int count) {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT co FROM ContractEntity AS co " +
                "WHERE co.customerCode = :code " +
                "ORDER BY co.contractCode DESC";
        Query query = entity.createQuery(hql);
        query.setParameter("code", custCode);
        query.setFirstResult(offset);
        query.setMaxResults(count);
        List resultList = query.getResultList();
        entity.close();
        return resultList;
    }

    public Long getContractByCustomerCodeCount(String customerCode) {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT COUNT(co) FROM ContractEntity AS co " +
                "WHERE co.customerCode = :code " +
                "ORDER BY co.contractCode DESC";
        Query query = entity.createQuery(hql);
        query.setParameter("code", customerCode);
        Long result = (Long) query.getSingleResult();
        entity.close();
        return result;
    }

    @Override
    protected String getCodePrefix() {
        return "HD";
    }

    /**
     * Search for contracts by contract code or customer name of the contract
     *
     * @param keyword
     * @return
     */
    public List<ContractSearchResultDto> searchContracts(String keyword) {

        EntityManager entityManager = factory.createEntityManager();
        TypedQuery<ContractSearchResultDto> query = entityManager.createQuery(
                "SELECT NEW ContractSearchResultDto(co, cu) " +
                        "FROM ContractEntity co " +
                        "JOIN co.micCustomerByCustomerCode cu " +
                        "WHERE co.customerCode = cu.customerCode " +
                        "AND (co.contractCode LIKE :keyword OR cu.name LIKE :keyword) " +
                        "ORDER BY FIELD(co.status, :fieldOrder) DESC, co.contractCode DESC",
                ContractSearchResultDto.class
        );
        query.setParameter("keyword", "%" + keyword + "%");
        query.setParameter("fieldOrder",
                Constants.ContractStatus.NO_CARD);
        List<ContractSearchResultDto> resultList = query.getResultList();
        entityManager.close();
        return resultList;
    }

    public boolean isExistByPlate(String plate) {
        EntityManager entityManager = factory.createEntityManager();
        String hql = "SELECT co FROM ContractEntity AS co " +
                "WHERE co.plate = :plate AND co.status <> :cancelled AND co.status <> :expired AND co.status <> :pending";
        Query query = entityManager.createQuery(hql);
        query.setParameter("plate", plate);
        query.setParameter("cancelled", Constants.ContractStatus.CANCELLED);
        query.setParameter("expired", Constants.ContractStatus.EXPIRED);
        query.setParameter("pending", Constants.ContractStatus.PENDING);
        if (query.getResultList().size() == 0) {
            entityManager.close();
            return false;
        }
        entityManager.close();
        return true;
    }

    public Long getContractByCodeOrCustomerNameCount(String keyword) {

        EntityManager entityManager = factory.createEntityManager();
        Query query = entityManager.createQuery(
                "SELECT COUNT(co) " +
                        "FROM ContractEntity co " +
                        "JOIN co.micCustomerByCustomerCode cu " +
                        "WHERE co.customerCode = cu.customerCode " +
                        "AND (co.contractCode LIKE :keyword OR cu.name LIKE :keyword) "
        );
        query.setParameter("keyword", "%" + keyword + "%");
        Long resultList = (Long) query.getSingleResult();
        entityManager.close();
        return resultList;
    }

    public List getContractByCodeOrCustomerName(String keyword, int offset, int count) {

        EntityManager entityManager = factory.createEntityManager();
        Query query = entityManager.createQuery(
                "SELECT co " +
                        "FROM ContractEntity co " +
                        "JOIN co.micCustomerByCustomerCode cu " +
                        "WHERE co.customerCode = cu.customerCode " +
                        "AND (co.contractCode LIKE :keyword OR cu.name LIKE :keyword) " +
                        " ORDER BY co.contractCode DESC"
        );
        query.setParameter("keyword", "%" + keyword + "%");
        query.setFirstResult(offset);
        query.setMaxResults(count);
        List resultList = query.getResultList();
        entityManager.close();
        return resultList;
    }

    public List getCustomerContractByCode(String customerCode, String keyword, int offset, int count) {
        EntityManager entityManager = factory.createEntityManager();
        Query query = entityManager.createQuery(
                "SELECT co " +
                        "FROM ContractEntity co " +
                        "WHERE co.customerCode = :customerCode " +
                        "AND co.contractCode LIKE :keyword"
        );
        query.setParameter("customerCode", customerCode);
        query.setParameter("keyword", "%" + keyword + "%");
        query.setFirstResult(offset);
        query.setMaxResults(count);
        List resultList = query.getResultList();
        entityManager.close();
        return resultList;
    }

    public Long getCustomerContractByCodeCount(String customerCode, String keyword) {
        EntityManager entityManager = factory.createEntityManager();
        Query query = entityManager.createQuery(
                "SELECT COUNT(co) " +
                        "FROM ContractEntity co " +
                        "WHERE co.customerCode = :customerCode " +
                        "AND co.contractCode LIKE :keyword"
        );
        query.setParameter("customerCode", customerCode);
        query.setParameter("keyword", "%" + keyword + "%");
        Long result = (Long) query.getSingleResult();
        entityManager.close();
        return result;
    }
}
