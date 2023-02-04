package org.example.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static org.example.entity.Util.BASE_NAME;
import static org.example.entity.Util.TYPE_PASS;
import static org.example.entity.Util.TYPE_URL;
import static org.example.entity.Util.TYPE_USER;

/**
 * @author Katerina
 * @version 1.0
 * класс с данными для подключения к jdbc.
 */
public final class DataProperties {
    /**
     * перечень свойств данных для подключения.
     */
    private static final ResourceBundle PROPERTIES =
            ResourceBundle.getBundle(BASE_NAME);
    /**
     * получение данных url из перечня свойств.
     */
    public static final String URL = PROPERTIES.getString(TYPE_URL);
    /**
     * получение данных пользователя из перечня свойств.
     */
    public static final String USER = PROPERTIES.getString(TYPE_USER);
    /**
     * получение данных пароля из перечня свойств.
     */

    public static final String PASS = PROPERTIES.getString(TYPE_PASS);

    /**
     * конструктор по умолчанию.
     */
    private DataProperties() {
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
