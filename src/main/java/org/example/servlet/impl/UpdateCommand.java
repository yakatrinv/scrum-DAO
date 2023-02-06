package org.example.servlet.impl;

import org.example.entity.Person;
import org.example.services.MappingService;
import org.example.services.ScrumSwrvice;
import org.example.services.dto.PersonDto;
import org.example.services.impl.PersonMappingService;
import org.example.services.impl.ScrumServiceImpl;
import org.example.servlet.ServletCommand;

import javax.servlet.http.HttpServletRequest;

import static org.example.entity.Util.GET;
import static org.example.entity.Util.INDEX_JSP;
import static org.example.entity.Util.JSP_PEOPLE_EDIT_JSP;
import static org.example.entity.Util.NAME;
import static org.example.entity.Util.PERSON;
import static org.example.entity.Util.PERSON_ID;
import static org.example.entity.Util.SURNAME;

public final class UpdateCommand implements ServletCommand {
    @Override
    public String execute(final HttpServletRequest request) {
        ScrumSwrvice scrumSwrvice = new ScrumServiceImpl();
        int id = Integer.parseInt(request.getParameter(PERSON_ID));
        MappingService<Person, PersonDto> personMappingService
                = new PersonMappingService();
        if (request.getMethod().equals(GET)) {
            Person person = (Person) scrumSwrvice.findById(
                    Person.builder().id(id).build());
            PersonDto personDTO = personMappingService.convertToDTO(person);
            request.setAttribute(PERSON, personDTO);
            return JSP_PEOPLE_EDIT_JSP;
        } else {
            PersonDto personDTO = PersonDto.builder().id(id)
                    .name(request.getParameter(NAME))
                    .surname(request.getParameter(SURNAME)).build();
            Person person = personMappingService.convertToEntity(personDTO);
            scrumSwrvice.update(person);
            return INDEX_JSP;
        }
    }
}
