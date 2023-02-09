package org.example.servlet.extractor.impl;

import org.example.services.dto.PersonDto;
import org.example.servlet.extractor.DtoExtractor;

import javax.servlet.http.HttpServletRequest;

import static org.example.entity.Util.NAME;
import static org.example.entity.Util.SURNAME;

/**
 * extracts PersonDto objects from request.
 */
public final class PersonDtoExtractor implements DtoExtractor<PersonDto> {
    @Override
    public PersonDto extract(final HttpServletRequest request) {
        return PersonDto.builder().name(request.getParameter(NAME))
                .surname(request.getParameter(SURNAME)).build();
    }
}
