package org.example.dao;

import org.example.entity.Person;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class PersonDAO extends DAO{
    public PersonDAO(final EntityManager newEntityManager) {
        super(newEntityManager);
    }
    public abstract List<Person> selectAll();
}
