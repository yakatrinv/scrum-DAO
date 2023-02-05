package org.example.service;

import org.example.dao.impl.PersonDaoImpl;
import org.example.dao.DAO;

import java.io.Serializable;

public interface DaoService {
    default void create(Object object) {
        DAO dao = new PersonDaoImpl(HibernateUtil.getEntityManager());
        dao.insert(object);
    }

    default Object read(Class<?> newClass, Serializable id) {
        DAO dao = new PersonDaoImpl(HibernateUtil.getEntityManager());
        return dao.select(newClass, id);
    }

    default void update(Object object) {
        DAO dao = new PersonDaoImpl(HibernateUtil.getEntityManager());
        dao.update(object);
    }

    default void delete(Object object) {
        DAO dao = new PersonDaoImpl(HibernateUtil.getEntityManager());
        dao.delete(object);
    }
}
