package org.example.dao;

import org.example.entity.Person;

import java.util.List;

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
     * @return id добавленной сущности.
     */
    int createPerson(Person person);

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
     * @param person тип сущности
     * @return найденная сущность.
     */
    Object findPerson(Person person);

    /**
     * поиск всех сущностей в базе данных по id объекта класса Person.
     *
     * @param personClass класс
     * @return список найденных сущностей.
     */
    List<Object> findAllPerson(Class<Person> personClass);
}
