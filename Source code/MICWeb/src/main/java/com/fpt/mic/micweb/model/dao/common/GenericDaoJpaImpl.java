package com.fpt.mic.micweb.model.dao.common;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author http://stackoverflow.com/a/3889814/1420186
 *         Modified by trungdq88@gmail.com - 02 Mar 2015
 * @param <T>
 * @param <PK>
 */
public class GenericDaoJpaImpl<T, PK extends Serializable>
        implements GenericDao<T, PK> {

    protected Class<T> entityClass;

    /**
     * This variable HAVE TO be static or it will create new connection
     * every time a derived class of this is created, which causes "too many connections" error.
     */
    public static EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("MicPersistenceUnit");

    public GenericDaoJpaImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass
                .getActualTypeArguments()[0];
    }

    public GenericDaoJpaImpl(Class<T> type) {
        this.entityClass = type;
    }

    @Override
    public T create(T t) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(t);
        entityManager.getTransaction().commit();
        entityManager.close();
        return t;
    }

    @Override
    public T read(PK id) {
        EntityManager entityManager = factory.createEntityManager();
        T t = entityManager.find(entityClass, id);
        entityManager.close();
        return t;
    }

    @Override
    public T update(T t) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        T result = entityManager.merge(t);
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

    @Override
    public void delete(T t) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        t = entityManager.merge(t);
        entityManager.remove(t);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
