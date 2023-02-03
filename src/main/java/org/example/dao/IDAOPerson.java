package org.example.dao;

import org.example.entity.Person;

/**
 * @author Katerina
 * @version 1.0
 * интерфейс для работы с опреациями crud для класса Person.
 */
public interface IDAOPerson extends IDAO<Person> {
    /**
     * добавление элземплара класса Person в базу данных.
     *
     * @param person тип сущности.
     */
    void createPerson(Person person);

    /**
     * изменение сущности в базе данных на значения элземплара класса Person.
     *
     * @param person тип сущности.
     */
    void updatePerson(Person person);

    /**
     * удаление сущности в базе данных по id объекта класса Person.
     *
     * @param person тип сущности.
     */
    void deletePerson(Person person);

    /**
     * поиск сущности в базе данных по id объекта класса Person.
     *
     * @param person тип сущности.
     */
    void findPerson(Person person);
}
