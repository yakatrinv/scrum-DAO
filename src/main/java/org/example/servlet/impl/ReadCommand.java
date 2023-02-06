package org.example.servlet.impl;

import org.example.entity.Person;
import org.example.services.MappingService;
import org.example.services.ScrumSwrvice;
import org.example.services.dto.PersonDto;
import org.example.services.impl.PersonMappingService;
import org.example.services.impl.ScrumServiceImpl;
import org.example.servlet.ServletCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.entity.Util.JSP_PEOPLE_INDEX_JSP;
import static org.example.entity.Util.PEOPLE;

/**
 * @author Alexey
 * @version 1.0
 * implements ServletCommand.
 */
public final class ReadCommand implements ServletCommand {
    @Override
    public String execute(final HttpServletRequest request) {
        ScrumSwrvice scrumSwrvice = new ScrumServiceImpl();
        List<Person> people = scrumSwrvice.findAll(Person.class).stream()
                .map(x -> (Person) x).collect(Collectors.toList());
        MappingService<Person, PersonDto> personMappingService
                = new PersonMappingService();
        List<PersonDto> personDTOList = people.stream()
                .map(personMappingService::convertToDTO)
                .collect(Collectors.toList());
        request.setAttribute(PEOPLE, personDTOList);
        return JSP_PEOPLE_INDEX_JSP;
    }
}
