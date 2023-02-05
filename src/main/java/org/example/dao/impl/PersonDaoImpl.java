package org.example.dao.impl;

import org.example.dao.PersonDAO;
import org.example.entity.Person;

import javax.persistence.EntityManager;
import java.util.List;

import static org.example.entity.Constant.FROM_PERSON;
import static org.example.entity.Constant.UNCHECKED;

public final class PersonDaoImpl extends PersonDAO {
    public PersonDaoImpl(final EntityManager newEntityManager) {
        super(newEntityManager);
    }

    @SuppressWarnings(UNCHECKED)
    @Override
    public List<Person> selectAll() {
        getEntityManager().getTransaction().begin();
        List<Person> people = getEntityManager().createQuery(FROM_PERSON)
                .getResultList();
        getEntityManager().getTransaction().commit();
        return people;
    }
}
