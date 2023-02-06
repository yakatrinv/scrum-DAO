package org.example.dao;

import org.example.entity.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.dao.TestConstant.CONRAD;
import static org.example.dao.TestConstant.FIRST_INDEX;
import static org.example.dao.TestConstant.ID_COLUMN_TITLE;
import static org.example.dao.TestConstant.NAME_COLUMN_TITLE;
import static org.example.dao.TestConstant.POTTER;
import static org.example.dao.TestConstant.SELECT_PERSON_BY_ID_SQL;
import static org.example.dao.TestConstant.SURNAME_COLUMN_TITLE;

public final class MockUtil {
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

    public static Person createTestPerson() {
        return Person.builder()
                .name(CONRAD)
                .surname(POTTER)
                .build();
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
