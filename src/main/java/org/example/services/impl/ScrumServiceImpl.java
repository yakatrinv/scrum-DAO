package org.example.services.impl;

import org.example.dao.DAOServicePerson;
import org.example.entity.Person;
import org.example.reposotiry.JDBCConnection;
import org.example.services.ScrumSwrvice;

import java.util.List;

/**
 * @author Katerina
 * @version 1.0
 * имплементация ScrumService для класса Person.
 */
public class ScrumServiceImpl implements ScrumSwrvice {

    /**
     * сщздаем экземпляр класса DAOServicePerson.
     */
    private final DAOServicePerson daoServicePerson = new DAOServicePerson();

    /**
     * добавление элземплара класса Person в базу данных.
     */
    @Override
    public void create(final Person person) {
        daoServicePerson.setConnection(JDBCConnection.initConnection());
        daoServicePerson.createPerson(person);
        JDBCConnection.closeConnection(daoServicePerson);
    }

    /**
     * изменение сущности в базе данных на значения элземплара класса Person.
     */
    @Override
    public void update(final Person person) {
        daoServicePerson.setConnection(JDBCConnection.initConnection());
        daoServicePerson.updatePerson(person);
        JDBCConnection.closeConnection(daoServicePerson);
    }

    /**
     * удаление сущности в базе данных по id объекта класса Person.
     */
    @Override
    public void delete(final Person person) {
        daoServicePerson.setConnection(JDBCConnection.initConnection());
        daoServicePerson.deletePerson(person);
        JDBCConnection.closeConnection(daoServicePerson);
    }

    /**
     * поиск сущности в базе данных по id объекта класса Person.
     */
    @Override
    public Object findById(final Person person) {
        daoServicePerson.setConnection(JDBCConnection.initConnection());
        Object foundPerson = daoServicePerson.findPersonById(person);
        JDBCConnection.closeConnection(daoServicePerson);
        return foundPerson;
    }

    /**
     * поиск всех сущностей в базе данных по id объекта класса Person.
     */
    @Override
    public List<Object> findAll(final Class<Person> personClass) {
        daoServicePerson.setConnection(JDBCConnection.initConnection());
        List<Object> foundPeople = daoServicePerson.findAllPerson(personClass);
        JDBCConnection.closeConnection(daoServicePerson);
        return foundPeople;
    }
}
