package org.example.servlet.impl;

import org.example.entity.Constant;
import org.example.entity.Person;
import org.example.entity.PersonDTO;
import org.example.service.DaoService;
import org.example.service.MappingService;
import org.example.service.impl.PersonDaoServiceImpl;
import org.example.service.impl.PersonMappingService;
import org.example.servlet.ServletCommand;

import javax.servlet.http.HttpServletRequest;

public final class CreateCommand implements ServletCommand {
    @Override
    public String execute(final HttpServletRequest request) {
        DaoService daoService = new PersonDaoServiceImpl();
        MappingService<Person, PersonDTO> personMappingService
                = new PersonMappingService();
        if (request.getMethod().equals(Constant.GET)) {
            return Constant.JSP_PEOPLE_NEW_JSP;
        } else {
            PersonDTO personDTO = PersonDTO.builder()
                    .name(request.getParameter(Constant.NAME))
                    .surname(request.getParameter(Constant.SURNAME)).build();
            Person person = personMappingService.convertToEntity(personDTO);
            daoService.create(person);
            return Constant.INDEX_JSP;
        }
    }
}
