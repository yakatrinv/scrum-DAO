package org.example.services.impl;

import org.example.dao.DAOServicePerson;
import org.example.entity.Person;
import org.example.reposotiry.JDBCConnection;
import org.example.services.MappingService;
import org.example.services.ScrumSwrvice;
import org.example.services.dto.PersonDto;

import java.util.List;
import java.util.stream.Collectors;

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
     * converts objects between Person and PersonDto.
     */
    private final MappingService<Person, PersonDto> personMappingService
            = new PersonMappingService();

    /**
     * добавление элземплара класса Person в базу данных.
     */
    @Override
    public void create(final PersonDto personDto) {
        daoServicePerson.setConnection(JDBCConnection.initConnection());
        Person person = personMappingService.convertToEntity(personDto);
        daoServicePerson.createPerson(person);
        personDto.setId(person.getId());
        JDBCConnection.closeConnection(daoServicePerson);
    }

    /**
     * изменение сущности в базе данных на значения элземплара класса Person.
     */
    @Override
    public void update(final PersonDto personDto) {
        daoServicePerson.setConnection(JDBCConnection.initConnection());
        daoServicePerson.updatePerson(
                personMappingService.convertToEntity(personDto));
        JDBCConnection.closeConnection(daoServicePerson);
    }

    /**
     * удаление сущности в базе данных по id объекта класса Person.
     */
    @Override
    public void delete(final int id) {
        daoServicePerson.setConnection(JDBCConnection.initConnection());
        daoServicePerson.deletePerson(Person.builder().id(id).build());
        JDBCConnection.closeConnection(daoServicePerson);
    }

    /**
     * поиск сущности в базе данных по id объекта класса Person.
     */
    @Override
    public PersonDto findById(final int id) {
        daoServicePerson.setConnection(JDBCConnection.initConnection());
        Object foundPerson = daoServicePerson.findPersonById(
                Person.builder().id(id).build());
        JDBCConnection.closeConnection(daoServicePerson);
        return personMappingService.convertToDTO((Person) foundPerson);
    }

    /**
     * поиск всех сущностей в базе данных по id объекта класса Person.
     */
    @Override
    public List<PersonDto> findAll() {
        daoServicePerson.setConnection(JDBCConnection.initConnection());
        List<Object> foundPeople = daoServicePerson.findAllPerson(Person.class);
        JDBCConnection.closeConnection(daoServicePerson);
        return foundPeople.stream().map(x -> personMappingService.convertToDTO(
                (Person) x)).collect(Collectors.toList());
    }
}
