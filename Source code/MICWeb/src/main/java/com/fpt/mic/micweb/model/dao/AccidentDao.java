package com.fpt.mic.micweb.model.dao;
<<<<<<< HEAD
import com.fpt.mic.micweb.model.dao.common.GenericDaoJpaImpl;
import com.fpt.mic.micweb.model.entity.AccidentEntity;
=======

import com.fpt.mic.micweb.model.dao.common.GenericDaoJpaImpl;
import com.fpt.mic.micweb.model.entity.AccidentEntity;

>>>>>>> origin/master
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
<<<<<<< HEAD
 * Created by PhucNguyen on 05/07/2015.
 */
public class AccidentDao  extends GenericDaoJpaImpl<AccidentEntity, Integer>{
    public List<AccidentEntity> getAllAccidentByContractCode(String contractCode) {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT ac FROM AccidentEntity AS ac " +
                "WHERE ac.contractCode = :code";
        Query query = entity.createQuery(hql);
        query.setParameter("code", contractCode);
        return query.getResultList();
=======
 * Created by Kha on 05/07/2015.
 */
public class AccidentDao extends GenericDaoJpaImpl<AccidentEntity, Integer> {
    public List<AccidentEntity> getAccidentByContractCode(String contractCode) {
        EntityManager entityManager = factory.createEntityManager();
        String hql = "SELECT a FROM AccidentEntity AS a " +
                "WHERE a.contractCode = :code " +
                "ORDER BY a.id DESC";
        Query query = entityManager.createQuery(hql);
        query.setParameter("code", contractCode);
        List<AccidentEntity> resultList = query.getResultList();
        entityManager.close();
        return resultList;
>>>>>>> origin/master
    }
}
