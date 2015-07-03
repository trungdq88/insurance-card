package com.fpt.mic.micweb.model.dao;

import com.fpt.mic.micweb.model.dao.common.GenericDaoJpaImpl;
import com.fpt.mic.micweb.model.dao.helper.NewCardRequestDao;
import com.fpt.mic.micweb.model.dto.form.NewCardRequestDto;
import com.fpt.mic.micweb.model.entity.CardEntity;
import com.fpt.mic.micweb.model.entity.NewCardRequestEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.Date;
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

    public CardEntity getCardByContract(String contractCode) {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT ca FROM CardEntity ca WHERE ca.contractCode = :contractCode " +
                "AND ca.deactivatedDate = NULL";
        Query query = entity.createQuery(hql);
        query.setParameter("contractCode", contractCode);
        try {
            return (CardEntity) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }



    /**
     * Check card status
     * If card is deactivated, returns null
     * @param cardID
     * @return
     */
    public CardEntity checkCard(String cardID) {
        EntityManager entity = factory.createEntityManager();
        String hql = "SELECT ca FROM CardEntity ca WHERE ca.cardId = :cardID " +
                "AND ca.deactivatedDate = NULL";
        Query query = entity.createQuery(hql);
        query.setParameter("cardID", cardID);
        try {
            return (CardEntity) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
