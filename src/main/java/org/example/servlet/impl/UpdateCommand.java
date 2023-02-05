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

public final class UpdateCommand implements ServletCommand {
    @Override
    public String execute(final HttpServletRequest request) {
        DaoService personDaoService = new PersonDaoServiceImpl();
        int id = Integer.parseInt(request.getParameter(Constant.PERSON_ID));
        MappingService<Person, PersonDTO> personMappingService
                = new PersonMappingService();
        if (request.getMethod().equals(Constant.GET)) {
            Person person = (Person) personDaoService.read(Person.class, id);
            PersonDTO personDTO = personMappingService.convertToDTO(person);
            request.setAttribute(Constant.PERSON, personDTO);
            return Constant.JSP_PEOPLE_EDIT_JSP;
        } else {
            PersonDTO personDTO = PersonDTO.builder().id(id)
                    .name(request.getParameter(Constant.NAME))
                    .surname(request.getParameter(Constant.SURNAME)).build();
            Person person = personMappingService.convertToEntity(personDTO);
            personDaoService.update(person);
            return Constant.INDEX_JSP;
        }
    }
}
