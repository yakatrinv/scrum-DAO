package org.example.servlet.impl;

import org.example.services.PersonDaoService;
import org.example.services.impl.PersonDaoServiceImpl;
import org.example.servlet.ServletCommand;

import javax.servlet.http.HttpServletRequest;

import static org.example.entity.Util.INDEX_JSP;
import static org.example.entity.Util.PERSON_ID;

/**
 * @author Alexey
 * @version 1.0
 * implements ServletCommand.
 */
public final class DeleteCommand implements ServletCommand {
    /**
     * service working with dao layer.
     */
    private final PersonDaoService personDaoService
            = new PersonDaoServiceImpl();

    @Override
    public String execute(final HttpServletRequest request) {
        personDaoService.deleteById(
                Integer.parseInt(request.getParameter(PERSON_ID)));
        return INDEX_JSP;
    }
}
