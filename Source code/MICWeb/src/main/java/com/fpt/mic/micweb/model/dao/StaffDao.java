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
    public List getAllNewCardRequests() {
        EntityManager entityManager = factory.createEntityManager();
        Query query = entityManager.createQuery("SELECT s FROM NewCardRequestEntity s");
        List resultList = query.getResultList();
        entityManager.close();
        return resultList;
    }

    /**
     * Get all staffs with offset and count
     * @param offset
     * @param count
     * @return
     */
    public List getOnePageStaff(int offset, int count) {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT co FROM StaffEntity co ORDER BY co.staffCode DESC";
        Query query = entity.createQuery(hql);
        query.setFirstResult(offset);
        query.setMaxResults(count);
        List resultList = query.getResultList();
        entity.close();
        return resultList;
    }
    public List getOnePageNewCardRequest(int offset, int count) {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT co FROM NewCardRequestEntity co ORDER BY co.resolveDate asc";
        Query query = entity.createQuery(hql);
        query.setFirstResult(offset);
        query.setMaxResults(count);
        List resultList = query.getResultList();
        entity.close();
        return resultList;
    }
    public List searchOnePageNewCardRequest(String finalKeyword, int offset, int count) {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT co FROM NewCardRequestEntity co " +
                "WHERE co.micCardInstanceByOldCardInstanceId.cardId LIKE :keyword " +
                "ORDER BY co.resolveDate asc";
        Query query = entity.createQuery(hql);
        query.setParameter("keyword", "%" + finalKeyword + "%");
        query.setFirstResult(offset);
        query.setMaxResults(count);
        List resultList = query.getResultList();
        entity.close();
        return resultList;
    }

    public Long getAllStaffCount() {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT COUNT(co) FROM StaffEntity co ORDER BY co.staffCode DESC";
        Query query = entity.createQuery(hql);
        Long result = (Long) query.getSingleResult();
        entity.close();
        return result;
    }

    public Long getAllNewCardRequestCount() {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT COUNT(co) FROM NewCardRequestEntity co ORDER BY co.id DESC";
        Query query = entity.createQuery(hql);
        Long result = (Long) query.getSingleResult();
        entity.close();
        return result;
    }

    public Long searchAllNewCardRequestCount(String finalKeyword) {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT COUNT(co) FROM NewCardRequestEntity co " +
                "WHERE co.micCardInstanceByOldCardInstanceId.cardId LIKE :keyword " +
                "ORDER BY co.id DESC";
        Query query = entity.createQuery(hql);
        query.setParameter("keyword", "%" + finalKeyword + "%");
        Long result = (Long) query.getSingleResult();
        entity.close();
        return result;
    }

    public Long getUnresolvedNewCardRequestCount() {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT COUNT(co) FROM NewCardRequestEntity co " +
                "WHERE co.resolveDate = NULL ORDER BY co.id DESC";
        Query query = entity.createQuery(hql);
        Long result = (Long) query.getSingleResult();
        entity.close();
        return result;
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
    public boolean isExistByEmail(String email) {
        EntityManager entityManager = factory.createEntityManager();
        String hql = "SELECT c FROM StaffEntity c WHERE c.email= :email";
        Query query = entityManager.createQuery(hql);
        query.setParameter("email", email);
        if (query.getResultList().size() == 0) {
            entityManager.close();
            return false;
        }
        entityManager.close();
        return true;
    }
    public boolean isExistByCode(String staffCode) {
        EntityManager entityManager = factory.createEntityManager();
        String hql = "SELECT c FROM StaffEntity c WHERE c.staffCode= :staffCode";
        Query query = entityManager.createQuery(hql);
        query.setParameter("staffCode", staffCode);
        if (query.getResultList().size() == 0) {
            entityManager.close();
            return false;
        }
        entityManager.close();
        return true;
    }
}
