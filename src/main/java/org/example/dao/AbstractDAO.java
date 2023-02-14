package org.example.dao;


import org.example.services.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;

import static org.example.entity.Util.FROM_HIBERNATE_CLASS;
import static org.example.entity.Util.UNCHECKED;

public abstract class AbstractDAO<T, V> {
    private final EntityManager entityManager
            = HibernateUtil.getEntityManager();
    private Class<T> entityClass;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityClass(final Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T insert(final T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    public T selectById(final V id) {
        entityManager.getTransaction().begin();
        T entity = entityManager.find(entityClass, id);
        entityManager.getTransaction().commit();
        return entity;
    }

    public T update(final T entity) {
        entityManager.getTransaction().begin();
        T updatedEntity = entityManager.merge(entity);
        entityManager.getTransaction().commit();
        return updatedEntity;
    }

    public void delete(final T entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(entity) ? entity
                : entityManager.merge(entity));
        entityManager.getTransaction().commit();
    }

    public void deleteById(final V id) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(entityClass, id));
        entityManager.getTransaction().commit();
    }

    @SuppressWarnings(UNCHECKED)
    public List<T> selectAll() {
        entityManager.getTransaction().begin();
        entityManager.clear();
        List<T> entities = entityManager.createQuery(
                String.format(FROM_HIBERNATE_CLASS,
                        entityClass.getSimpleName())).getResultList();
        entityManager.getTransaction().commit();
        return entities;
    }
}
