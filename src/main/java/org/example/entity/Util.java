package org.example.entity;

/**
 * Утилитный класс для хранения констатнт.
 */
public final class Util {
    /**
     * путь к базе данных.
     */
    public static final String TYPE_URL = "url";
    /**
     * название базы данных.
     */
    public static final String BASE_NAME = "database";
    /**
     * имя пользователя.
     */
    public static final String TYPE_USER = "user";
    /**
     * пароль пользователя.
     */
    public static final String TYPE_PASS = "password";

    //QUERY

    /**
     * SQL шаблон для добавления записи в базу данных
     * используется при формировании строки запроса.
     */
    public static final String INSERT_QUERY
            = "INSERT INTO %s (%s) VALUES (%s);";
    /**
     * SQL шаблон для выбора записей в базу данных
     * используется при формировании строки запроса.
     */
    public static final String SELECT_QUERY = "SELECT * FROM %s WHERE %s = %s;";
    /**
     * SQL шаблон для выбора всех записей в базу данных
     * используется при формировании строки запроса.
     */
    public static final String SELECT_ALL_QUERY = "SELECT * FROM %s;";
    /**
     * SQL шаблон для обновления записи в базу данных
     * используется при формировании строки запроса.
     */
    public static final String UPDATE_QUERY = "UPDATE %s SET %s WHERE %s = ?;";
    /**
     * SQL шаблон для удаления записи в базу данных
     * используется при формировании строки запроса.
     */
    public static final String DELETE_QUERY = "DELETE FROM %s WHERE %s = ?;";
    /**
     * символ для указания параметра в запросе
     * используется при формировании строки запроса.
     */
    public static final String CHAR_QUESTION = "?";
    /**
     * delimiter for columns.
     */
    public static final String DELIMITER = ",";
    /**
     * delimiter for columns in update query.
     */
    public static final String DELIMITER_UPD = " = ? ,";
    /**
     * delimiter for columns in update query in the end.
     */
    public static final String DELIM_UPD_END = " = ?";

    /**
     * name of attribute for jsp view.
     */
    public static final String PEOPLE = "people";
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
