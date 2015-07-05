package com.fpt.mic.micweb.model.dao;
import com.fpt.mic.micweb.model.dao.common.GenericDaoJpaImpl;
import com.fpt.mic.micweb.model.entity.AccidentEntity;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
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
    }
}
