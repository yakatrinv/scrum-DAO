package org.example.servlet.impl;

import org.example.entity.Constant;
import org.example.entity.Person;
import org.example.service.DaoService;
import org.example.service.impl.PersonDaoServiceImpl;
import org.example.servlet.ServletCommand;

import javax.servlet.http.HttpServletRequest;

public final class DeleteCommand implements ServletCommand {
    @Override
    public String execute(final HttpServletRequest request) {
        DaoService daoService = new PersonDaoServiceImpl();
        daoService.delete(new Person(Integer.parseInt(
                request.getParameter(Constant.PERSON_ID)), null, null));
        return Constant.INDEX_JSP;
    }
}
