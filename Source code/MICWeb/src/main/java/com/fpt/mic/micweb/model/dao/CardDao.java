package com.fpt.mic.micweb.model.dao;

import com.fpt.mic.micweb.model.dao.common.GenericDaoJpaImpl;
import com.fpt.mic.micweb.model.entity.CardEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by dinhquangtrung on 6/2/15.
 */
public class CardDao extends GenericDaoJpaImpl<CardEntity, String> {

    public List<CardEntity> getAllCard() {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT ca FROM CardEntity ca";
        Query query = entity.createQuery(hql);
        return query.getResultList();
    }
}
