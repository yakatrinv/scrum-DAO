package org.example.servlet.impl;

import org.example.entity.Person;
import org.example.services.ScrumSwrvice;
import org.example.services.impl.ScrumServiceImpl;
import org.example.servlet.ServletCommand;

import javax.servlet.http.HttpServletRequest;

import static org.example.entity.Util.INDEX_JSP;
import static org.example.entity.Util.PERSON_ID;

public final class DeleteCommand implements ServletCommand {
    @Override
    public String execute(final HttpServletRequest request) {
        ScrumSwrvice scrumSwrvice = new ScrumServiceImpl();
        scrumSwrvice.delete(new Person(Integer.parseInt(
                request.getParameter(PERSON_ID)), null, null));
        return INDEX_JSP;
    }
}
