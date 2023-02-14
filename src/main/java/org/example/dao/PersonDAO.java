package org.example.dao;

import org.example.entity.Person;

public class PersonDAO extends AbstractDAO<Person, Integer> {
    public PersonDAO() {
        setEntityClass(Person.class);
    }
}
