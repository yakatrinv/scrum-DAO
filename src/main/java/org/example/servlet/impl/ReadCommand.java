package org.example.servlet.impl;

import org.example.entity.PersonDto;
import org.example.services.PersonDaoService;
import org.example.services.impl.PersonDaoServiceImpl;
import org.example.servlet.ServletCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.example.entity.Util.JSP_PEOPLE_INDEX_JSP;
import static org.example.entity.Util.PEOPLE;

/**
 * @author Alexey
 * @version 1.0
 * implements ServletCommand.
 */
public final class ReadCommand implements ServletCommand {
    /**
     * service working with dao layer.
     */
    private final PersonDaoService personDaoService
            = new PersonDaoServiceImpl();

    @Override
    public String execute(final HttpServletRequest request) {
        List<PersonDto> personDTOList = personDaoService.readAll();
        request.setAttribute(PEOPLE, personDTOList);
        return JSP_PEOPLE_INDEX_JSP;
    }
}
