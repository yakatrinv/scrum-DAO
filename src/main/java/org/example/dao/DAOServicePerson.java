package org.example.dao;

import org.example.entity.Person;

import java.util.List;

/**
 * @author Katerina
 * @version 1.0
 * имплементация DAO для класса Person.
 */
public class DAOServicePerson extends DAOService<Person> implements IDAOPerson {
    /**
     * добавление элземплара класса Person в базу данных.
     */
    @Override
    public int createPerson(final Person person) {
        return this.insert(person);
    }
    /**
     * изменение сущности в базе данных на значения элземплара класса Person.
     */
    @Override
    public void updatePerson(final Person person) {
        this.update(person);
    }
    /**
     * удаление сущности в базе данных по id объекта класса Person.
     */
    @Override
    public void deletePerson(final Person person) {
        this.delete(person);
    }

    /**
     * поиск сущности в базе данных по id объекта класса Person.
     */
    @Override
    public Object findPerson(final Person person) {
        return this.select(person);
    }

    /**
     * поиск всех сущностей в базе данных по id объекта класса Person.
     */
    @Override
    public List<Object> findAllPerson(final Class<Person> personClass) {
        return this.selectAll(personClass);
    }
}
