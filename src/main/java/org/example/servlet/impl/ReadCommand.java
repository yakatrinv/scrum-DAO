package org.example.servlet.impl;

import org.example.entity.Constant;
import org.example.entity.Person;
import org.example.entity.PersonDTO;
import org.example.service.MappingService;
import org.example.service.PersonDaoService;
import org.example.service.impl.PersonDaoServiceImpl;
import org.example.service.impl.PersonMappingService;
import org.example.servlet.ServletCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public final class ReadCommand implements ServletCommand {
    @Override
    public String execute(final HttpServletRequest request) {
        PersonDaoService personDaoService = new PersonDaoServiceImpl();
        List<Person> people = personDaoService.readAll();
        MappingService<Person, PersonDTO> personMappingService
                = new PersonMappingService();
        List<PersonDTO> personDTOList = people.stream()
                .map(personMappingService::convertToDTO)
                .collect(Collectors.toList());
        request.setAttribute(Constant.PEOPLE, personDTOList);
        return Constant.JSP_PEOPLE_INDEX_JSP;
    }
}
