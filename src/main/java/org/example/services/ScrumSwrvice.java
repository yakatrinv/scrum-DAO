package org.example.services;

import org.example.services.dto.PersonDto;

import java.util.List;

/**
 * @author Katerina
 * @version 1.0
 * интерфейс для работы с опреациями crud для класса Person.
 */
public interface ScrumSwrvice {
    /**
     * добавление элземплара класса Person в базу данных.
     *
     * @param personDto тип сущности.
     */
    void create(PersonDto personDto);

    /**
     * изменение сущности в базе данных на значения элземплара класса Person.
     *
     * @param personDto тип сущности.
     */
    void update(PersonDto personDto);

    /**
     * удаление сущности в базе данных по id объекта класса Person.
     *
     * @param id person identification number.
     */
    void delete(int id);

    /**
     * поиск сущности в базе данных по id объекта класса Person.
     *
     * @param id person identification number.
     * @return найденная сущность.
     */
    PersonDto findById(int id);

    /**
     * поиск всех сущностей в базе данных по id объекта класса Person.
     *
     * @return список найденных сущностей.
     */
    List<PersonDto> findAll();
}

