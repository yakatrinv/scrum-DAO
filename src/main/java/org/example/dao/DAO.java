package org.example.dao;

import javax.persistence.EntityManager;
import java.io.Serializable;

public abstract class DAO {
    private final EntityManager entityManager;

    public DAO(final EntityManager newEntityManager) {
        entityManager = newEntityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void insert(Object object) {
        entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.getTransaction().commit();
    }

    public Object select(Class<?> newClass, Serializable id) {
        entityManager.getTransaction().begin();
        Object entity = entityManager.find(newClass, id);
        entityManager.getTransaction().commit();
        return entity;
    }

    public void update(Object object) {
        entityManager.getTransaction().begin();
        entityManager.merge(object);
        entityManager.getTransaction().commit();
    }

    public void delete(Object object) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(object) ? object
                : entityManager.merge(object));
        entityManager.getTransaction().commit();
    }
}
