package org.example.servlet.extractor.impl;

import org.example.entity.PersonDto;
import org.example.servlet.extractor.DtoExtractor;

import javax.servlet.http.HttpServletRequest;

import static org.example.entity.Util.NAME;
import static org.example.entity.Util.PERSON_ID;
import static org.example.entity.Util.SURNAME;

/**
 * extracts PersonDto objects from request.
 */
public final class PersonDtoExtractor implements DtoExtractor<PersonDto> {
    @Override
    public PersonDto extract(final HttpServletRequest request) {
        PersonDto personDto = PersonDto.builder()
                .name(request.getParameter(NAME))
                .surname(request.getParameter(SURNAME)).build();
        if (request.getParameter(PERSON_ID) != null) {
            personDto.setId(Integer.parseInt(request.getParameter(PERSON_ID)));
        }
        return personDto;
    }
}
