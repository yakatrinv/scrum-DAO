package org.example;

import org.example.impl.IDAOPerson;
/**
 * @author Katerina
 * @version 1.0
 * имплементация DAO для класса Person.
 */
public class DAOPerson extends DAO<Person> implements IDAOPerson {
    /**
     * добавление элземплара класса Person в базу данных.
     */
    @Override
    public void createPerson(final Person person) {
        this.insert(person);
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
    public void findPerson(final Person person) {
        this.select(person);
    }
}
