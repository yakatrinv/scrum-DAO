package org.example.services;

import org.example.entity.Person;

import java.util.List;

public interface ScrumSwrvice {
    /**
     * добавление элземплара класса Person в базу данных.
     *
     * @param person тип сущности.
     */
    void create(Person person);

    /**
     * изменение сущности в базе данных на значения элземплара класса Person.
     *
     * @param person тип сущности.
     */
    void update(Person person);

    /**
     * удаление сущности в базе данных по id объекта класса Person.
     *
     * @param person тип сущности.
     */
    void delete(Person person);

    /**
     * поиск сущности в базе данных по id объекта класса Person.
     *
     * @param person тип сущности.
     */
    Object findById(Person person);

    /**
     * поиск всех сущностей в базе данных по id объекта класса Person.
     *
     * @param personClass класс.
     */
    List<Object> findAll(Class<Person> personClass);
}

