package com.fpt.mic.micweb.model.dao.helper;

import com.fpt.mic.micweb.model.dao.common.GenericDaoJpaImpl;
import org.hibernate.metadata.ClassMetadata;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Table;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/19/15.
 */
public class ValidatorDao<T, PK extends Serializable> extends GenericDaoJpaImpl<T, PK> {
    public ValidatorDao() {
        super();
    }

    public ValidatorDao(Class<T> type) {
        super(type);
    }

    public boolean isExists(String fieldName, String fieldValue) {
        EntityManager entityManager = factory.createEntityManager();

        // Get table name
        Table table = entityClass.getAnnotation(Table.class);
        String tableName = table.name();

        // Get column name
        Method method = null;
        String columnName = "!!! COLUMN NOT FOUND !!!";
        try {
            method = entityClass.getMethod(fieldName);
            Column a = method.getAnnotation(Column.class);
            columnName = a.name();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        Query nativeQuery = entityManager.createNativeQuery
                ("SELECT COUNT(*) FROM " + tableName + " WHERE " + columnName + " = ?");
        nativeQuery.setParameter(1, fieldValue);
        BigInteger count = (BigInteger) nativeQuery.getSingleResult();
        entityManager.close();

        return count.intValue() > 0;
    }
}
