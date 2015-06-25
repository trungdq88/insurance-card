package com.fpt.mic.micweb.model.dao;

import com.fpt.mic.micweb.model.dao.common.GenericDaoJpaImpl;
import com.fpt.mic.micweb.model.dao.common.IncrementDao;
import com.fpt.mic.micweb.model.entity.CustomerEntity;
import com.fpt.mic.micweb.model.entity.StaffEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by dinhquangtrung on 6/2/15.
 */
public class StaffDao extends IncrementDao<StaffEntity, String> {

    @Override
    protected String getCodePrefix() {
        return "NV";
    }

    /**
     * Return ALL staffs in database. Should not use this because listing all
     * the staff in database is a bad practice, it should be paginated.
     * @return List<StaffEntity>
     */
    public List getAllStaffs() {
        EntityManager entityManager = factory.createEntityManager();
        Query query = entityManager.createQuery("SELECT s FROM StaffEntity s");
        List resultList = query.getResultList();
        entityManager.close();
        return resultList;
    }

    /**
     * Find staff by name
     * @param keyword
     * @return List<StaffEntity>
     */
    public List findStaffsByStaffCode(String keyword) {
        EntityManager entityManager = factory.createEntityManager();
        Query query = entityManager
                .createQuery("SELECT s FROM StaffEntity s WHERE s.staffCode LIKE :keyword");
        query.setParameter("keyword", "%" + keyword + "%");
        List resultList = query.getResultList();
        entityManager.close();
        return resultList;
    }

    public StaffEntity getStaffByEmail(String email) {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT s FROM StaffEntity s WHERE s.email = :email";
        Query query = entity.createQuery(hql);
        query.setParameter("email", email);
        try {
            return (StaffEntity) query.getSingleResult();
        } catch (NonUniqueResultException e) {
            return null;
        } catch (NoResultException e) {
            return null;
        }
    }
}
