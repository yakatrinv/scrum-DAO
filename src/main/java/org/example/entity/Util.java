package org.example.entity;

/**
 * Утилитный класс для хранения констатнт.
 */
public final class Util {
    /**
     * gets all objects of concrete hibernate entity.
     */
    public static final String FROM_HIBERNATE_CLASS = "FROM %s";
    /**
     * SuppressWarnings type.
     */
    public static final String UNCHECKED = "unchecked";
    /**
     * name of attribute for jsp view.
     */
    public static final String PEOPLE = "people";
    /**
     * table title.
     */
    public static final String ID = "id";
    /**
     * table title.
     */
    public static final String PERSON = "person";
    /**
     * table column.
     */
    public static final String NAME = "name";
    /**
     * table column.
     */
    public static final String SURNAME = "surname";
    /**
     * encoding variable title.
     */
    public static final String ENCODING = "encoding";
    /**
     * path to jsp, which creates new entities.
     */
    public static final String JSP_PEOPLE_NEW_JSP = "/jsp/people/new.jsp";
    /**
     * http method.
     */
    public static final String GET = "GET";
    /**
     * name of attribute for jsp view.
     */
    public static final String PERSON_ID = "personId";
    /**
     * path to default web page.
     */
    public static final String INDEX_JSP = "/index.jsp";
    /**
     * path to web page, containing collection of all people.
     */
    public static final String JSP_PEOPLE_INDEX_JSP = "/jsp/people/index.jsp";
    /**
     * path to jsp, which updates entities.
     */
    public static final String JSP_PEOPLE_EDIT_JSP = "/jsp/people/edit.jsp";
    /**
     * title of request attribute.
     */
    public static final String COMMAND = "command";
    /**
     * name of mysql driver.
     */
    public static final String COM_MYSQL_CJ_JDBC_DRIVER
            = "com.mysql.cj.jdbc.Driver";


    /**
     * default constructor.
     */
    private Util() {
    }
}
