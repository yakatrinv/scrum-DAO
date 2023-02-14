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
import static org.example.entity.Util.JSP_PEOPLE_NEW_JSP;

/**
 * @author Alexey
 * @version 1.0
 * implements ServletCommand.
 */
public final class CreateCommand implements ServletCommand {
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
            return JSP_PEOPLE_NEW_JSP;
        }
        personDaoService.create(personDtoExtractor.extract(request));
        return INDEX_JSP;
    }
}
