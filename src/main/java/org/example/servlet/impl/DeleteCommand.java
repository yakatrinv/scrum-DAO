package org.example.servlet.impl;

import org.example.services.ScrumSwrvice;
import org.example.services.impl.ScrumServiceImpl;
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
    private final ScrumSwrvice scrumSwrvice = new ScrumServiceImpl();
    @Override
    public String execute(final HttpServletRequest request) {
        scrumSwrvice.delete(Integer.parseInt(request.getParameter(PERSON_ID)));
        return INDEX_JSP;
    }
}
