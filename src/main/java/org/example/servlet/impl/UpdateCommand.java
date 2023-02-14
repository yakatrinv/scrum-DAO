package org.example.servlet.impl;

import org.example.entity.PersonDto;
import org.example.services.PersonDaoService;
import org.example.services.impl.PersonDaoServiceImpl;
import org.example.servlet.ServletCommand;
import org.example.servlet.extractor.DtoExtractor;
import org.example.servlet.extractor.impl.PersonDtoExtractor;

import javax.servlet.http.HttpServletRequest;

import static org.example.entity.Util.GET;
import static org.example.entity.Util.INDEX_JSP;
import static org.example.entity.Util.JSP_PEOPLE_EDIT_JSP;
import static org.example.entity.Util.PERSON;
import static org.example.entity.Util.PERSON_ID;

/**
 * @author Alexey
 * @version 1.0
 * implements ServletCommand.
 */
public final class UpdateCommand implements ServletCommand {
    /**
     * service working with dao layer.
     */
    private final PersonDaoService personDaoService
            = new PersonDaoServiceImpl();
    /**
     * extracts PersonDto objects from request.
     */
    private final DtoExtractor<PersonDto> personDtoExtractor
            = new PersonDtoExtractor();

    @Override
    public String execute(final HttpServletRequest request) {
        if (GET.equals(request.getMethod())) {
            int id = Integer.parseInt(request.getParameter(PERSON_ID));
            PersonDto personDTO = personDaoService.readById(id);
            request.setAttribute(PERSON, personDTO);
            return JSP_PEOPLE_EDIT_JSP;
        }
        PersonDto personDTO = personDtoExtractor.extract(request);
        personDaoService.update(personDTO);
        return INDEX_JSP;
    }
}
