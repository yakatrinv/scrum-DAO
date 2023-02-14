package org.example.services.impl;

import org.example.entity.Person;
import org.example.entity.PersonDto;
import org.example.services.MappingService;

/**
 * @author Alexey
 * @version 1.0
 * имплементация MappingService.
 */
public final class PersonMappingService
        implements MappingService<Person, PersonDto> {
    @Override
    public Person convertToEntity(final PersonDto dto) {
        return Person.builder().id(dto.getId()).name(dto.getName())
                .surname(dto.getSurname()).build();
    }

    @Override
    public PersonDto convertToDTO(final Person entity) {
        return PersonDto.builder().id(entity.getId()).name(entity.getName())
                .surname(entity.getSurname()).build();
    }
}
