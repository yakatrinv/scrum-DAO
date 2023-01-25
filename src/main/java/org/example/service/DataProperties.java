package org.example.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author Katerina
 * @version 1.0
 * класс с данными для подключения к jdbc.
 */
public final class DataProperties {
    /**
     * путь к базе данных.
     */
    private static final String TYPE_URL = "url";
    /**
     * название базы данных.
     */
    public static final String BASE_NAME = "database";
    /**
     * имя пользователя.
     */
    private static final String TYPE_USER = "user";
    /**
     * пароль пользователя.
     */
    private static final String TYPE_PASS = "password";

    /**
     * перечень свойств данных для подключения.
     */
    private static final ResourceBundle PROPERTIES = getProperties();
    /**
     * получение данных url из перечня свойств.
     */
    public static final String URL = getValue(TYPE_URL);
    /**
     * получение данных пользователя из перечня свойств.
     */
    public static final String USER = getValue(TYPE_USER);
    /**
     * получение данных пароля из перечня свойств.
     */

    public static final String PASS = getValue(TYPE_PASS);

    /**
     * конструктор по умолчанию.
     */
    private DataProperties() {
    }

    /**
     * @param type строка, с именем свойства
     * @return получение данных из перечня свойств по свойству.
     */

    private static String getValue(final String type) {
        return PROPERTIES.getString(type);
    }

    /**
     * @return возвращает перечень свойств базы данных.
     */
    private static ResourceBundle getProperties() {
        return ResourceBundle.getBundle(BASE_NAME);
    }

    /**
     * @return Возвращает соединение по данным базы данных.
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
