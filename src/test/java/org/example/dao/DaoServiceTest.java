package org.example.dao;

import org.example.entity.DataProperties;
import org.example.entity.Person;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.SQLException;
import java.util.stream.Stream;

import static org.example.dao.MockUtil.selectById;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

final class DaoServiceTest {
    public static final String STR_ADD_UPD = " upd";
    private static DAOServicePerson daoServicePerson;
    private static Person actualPerson;

    @BeforeAll
    static void setConnection() {
        daoServicePerson = new DAOServicePerson();
        daoServicePerson.setConnection(DataProperties.getConnection());
        actualPerson = MockUtil.createInsertPerson();
        daoServicePerson.insert(actualPerson);
    }


    private static Stream<Arguments> providePeopleForTestInsert() {
        return Stream.of(Arguments.of(actualPerson));
    }

    private static Stream<Arguments> providePeopleForTestSelect() {
        return Stream.of(Arguments.of(actualPerson));
    }


    private static Stream<Arguments> providePeopleForTestUpdate() {
        return Stream.of(Arguments.of(actualPerson));
    }


    private static Stream<Arguments> providePeopleForTestDelete() {
        return Stream.of(Arguments.of(actualPerson));
    }

    @ParameterizedTest
    @MethodSource("providePeopleForTestInsert")
    void testInsert(final Person person) throws SQLException {
        daoServicePerson.createPerson(person);
        Person expectedPerson = selectById(person.getId(),daoServicePerson.getConnection());

        assertNotNull(expectedPerson);
        assertAll("Equals all fields",
                () -> assertEquals(expectedPerson.getName(),person.getName()),
                () -> assertEquals(expectedPerson.getSurname(),person.getSurname())
        );
    }

    @ParameterizedTest
    @MethodSource("providePeopleForTestSelect")
    void testSelect(final Person person) throws SQLException {
        daoServicePerson.findPersonById(person);
        Person expectedPerson = selectById(person.getId(),daoServicePerson.getConnection());

        assertNotNull(expectedPerson);
        assertAll("Equals all fields",
                () -> assertEquals(expectedPerson.getName(),person.getName()),
                () -> assertEquals(expectedPerson.getSurname(),person.getSurname())
        );
    }

    @ParameterizedTest
    @MethodSource("providePeopleForTestUpdate")
    void testUpdate(final Person person) throws SQLException {
        person.setName(person.getName()+ STR_ADD_UPD);
        person.setSurname(person.getSurname()+ STR_ADD_UPD);
        daoServicePerson.updatePerson(person);
        Person expectedPerson = selectById(person.getId(),daoServicePerson.getConnection());

        assertNotNull(expectedPerson);
        assertAll("Equals all fields",
                () -> assertEquals(expectedPerson.getName(),person.getName()),
                () -> assertEquals(expectedPerson.getSurname(),person.getSurname())
        );
    }

    @ParameterizedTest
    @MethodSource("providePeopleForTestUpdate")
    void testDelete(final Person person) throws SQLException {
        daoServicePerson.deletePerson(person);
        Person expectedPerson = selectById(person.getId(),daoServicePerson.getConnection());

        assertNull(expectedPerson);
    }

    @AfterAll
    static void closeConnection() throws SQLException {
        daoServicePerson.getConnection().close();
    }

}
