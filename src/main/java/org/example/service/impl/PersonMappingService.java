package org.example.service.impl;

import org.example.entity.Person;
import org.example.entity.PersonDTO;
import org.example.service.MappingService;

public final class PersonMappingService
        implements MappingService<Person, PersonDTO> {
    @Override
    public Person convertToEntity(final PersonDTO dto) {
        return Person.builder().id(dto.getId()).name(dto.getName())
                .surname(dto.getSurname()).build();
    }

    @Override
    public PersonDTO convertToDTO(final Person entity) {
        return PersonDTO.builder().id(entity.getId()).name(entity.getName())
                .surname(entity.getSurname()).build();
    }
}
