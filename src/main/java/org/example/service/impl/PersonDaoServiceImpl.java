package org.example.service.impl;

import org.example.dao.impl.PersonDaoImpl;
import org.example.dao.PersonDAO;
import org.example.entity.Person;
import org.example.service.HibernateUtil;
import org.example.service.PersonDaoService;

import java.util.List;

public final class PersonDaoServiceImpl implements PersonDaoService {
    @Override
    public List<Person> readAll() {
        PersonDAO personDAO = new PersonDaoImpl(
                HibernateUtil.getEntityManager());
        return personDAO.selectAll();
    }
}
