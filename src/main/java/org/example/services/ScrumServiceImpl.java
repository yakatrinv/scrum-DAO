package org.example.services;

import liquibase.servicelocator.LiquibaseService;
import org.example.dao.DAOServicePerson;
import org.example.entity.Person;

import java.util.List;

public class ScrumServiceImpl implements ScrumSwrvice {

    DAOServicePerson daoServicePerson = new DAOServicePerson();

    @Override
    public void create(final Person person) {
        daoServicePerson.createPerson(person);
    }

    /**
     * изменение сущности в базе данных на значения элземплара класса Person.
     */
    @Override
    public void update(final Person person) {
        daoServicePerson.updatePerson(person);
    }

    /**
     * удаление сущности в базе данных по id объекта класса Person.
     */
    @Override
    public void delete(final Person person) {
        daoServicePerson.deletePerson(person);
    }

    /**
     * поиск сущности в базе данных по id объекта класса Person.
     */
    @Override
    public Object findById(final Person person) {
        return daoServicePerson.findPersonById(person);
    }

    /**
     * поиск всех сущностей в базе данных по id объекта класса Person.
     */
    @Override
    public List<Object> findAll(final Class<Person> personClass) {
        return daoServicePerson.findAllPerson(personClass);
    }
}
