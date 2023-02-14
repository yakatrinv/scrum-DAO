package org.example.services.impl;

import org.example.dao.PersonDAO;
import org.example.dao.impl.PersonDaoImpl;
import org.example.entity.Person;
import org.example.entity.PersonDto;
import org.example.services.MappingService;
import org.example.services.PersonDaoService;

import java.util.List;
import java.util.stream.Collectors;

public final class PersonDaoServiceImpl implements PersonDaoService {
    private final PersonDAO personDAO = new PersonDaoImpl();
    /**
     * converts objects between Person and PersonDto.
     */
    private final MappingService<Person, PersonDto> producerMappingService
            = new PersonMappingService();

    @Override
    public PersonDto create(final PersonDto dto) {
        Person entity = producerMappingService.convertToEntity(dto);
        Person personEntity = personDAO.insert(entity);
        return producerMappingService.convertToDTO(personEntity);
    }

    @Override
    public PersonDto readById(final Integer id) {
        Person personEntity = personDAO.selectById(id);
        return producerMappingService.convertToDTO(personEntity);
    }

    @Override
    public PersonDto update(final PersonDto dto) {
        Person entity = producerMappingService.convertToEntity(dto);
        Person personEntity = personDAO.update(entity);
        return producerMappingService.convertToDTO(personEntity);
    }

    @Override
    public void delete(final PersonDto dto) {
        Person entity = producerMappingService.convertToEntity(dto);
        personDAO.delete(entity);
    }

    @Override
    public void deleteById(final Integer id) {
        personDAO.deleteById(id);
    }

    @Override
    public List<PersonDto> readAll() {
        List<Person> producerEntities = personDAO.selectAll();
        return producerEntities.stream()
                .map(producerMappingService::convertToDTO)
                .collect(Collectors.toList());
    }
}