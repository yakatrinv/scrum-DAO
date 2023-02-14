package org.example;

import org.example.entity.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import static org.example.TestConstant.BASE_NAME;
import static org.example.TestConstant.CLEAR_PERSON_TABLE_SQL;
import static org.example.TestConstant.CONRAD;
import static org.example.TestConstant.FIRST_INDEX;
import static org.example.TestConstant.ID_COLUMN_TITLE;
import static org.example.TestConstant.INSERT_PERSON_SQL;
import static org.example.TestConstant.NAME_COLUMN_TITLE;
import static org.example.TestConstant.POTTER;
import static org.example.TestConstant.SECOND_INDEX;
import static org.example.TestConstant.SELECT_PERSON_BY_ID_SQL;
import static org.example.TestConstant.SURNAME_COLUMN_TITLE;
import static org.example.TestConstant.TYPE_PASS;
import static org.example.TestConstant.TYPE_URL;
import static org.example.TestConstant.TYPE_USER;

public final class MockUtil {
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

    public static Person createTestPerson() {
        return Person.builder()
                .name(CONRAD)
                .surname(POTTER)
                .build();
    }

    public static void clearPersonTable(Connection connection)
            throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                CLEAR_PERSON_TABLE_SQL)) {
            statement.executeUpdate();
        }
    }

    public static void insertPerson(Person person, Connection connection)
            throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                INSERT_PERSON_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(FIRST_INDEX, person.getName());
            statement.setString(SECOND_INDEX, person.getSurname());
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                person.setId(statement.getGeneratedKeys().getInt(FIRST_INDEX));
            }
        }
    }

    public static Person selectById(final int id, Connection connection)
            throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                SELECT_PERSON_BY_ID_SQL)) {
            statement.setInt(FIRST_INDEX, id);
            ResultSet resultset = statement.executeQuery();
            Person person = null;
            while (resultset.next()) {
                person = createPerson(resultset);
            }
            return person;
        }
    }

    private static Person createPerson(final ResultSet resultset)
            throws SQLException {
        Person person = new Person();
        person.setId(resultset.getInt(ID_COLUMN_TITLE));
        person.setName(resultset.getString(NAME_COLUMN_TITLE));
        person.setSurname(resultset.getString(SURNAME_COLUMN_TITLE));
        return person;
    }
}
