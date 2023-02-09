package org.example.dao;

import org.example.entity.Person;
import org.example.reposotiry.JDBCConnection;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

import static org.example.dao.MockUtil.selectById;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public final class DaoServiceTest {
    public static final String STR_ADD_UPD = " upd";
    public static final String EQUALS_ALL_FIELDS = "Equals all fields";
    private static DAOServicePerson daoServicePerson;

    @BeforeAll
    static void setConnection() {
        daoServicePerson = new DAOServicePerson();
        daoServicePerson.setConnection(JDBCConnection.initConnection());
    }

    private static Stream<Arguments> providePeopleForTestInsert() {
        return Stream.of(Arguments.of(MockUtil.createTestPerson()));
    }

    private static Stream<Arguments> providePeopleForTestSelect() {
        Person actualPerson = MockUtil.createTestPerson();
        if (daoServicePerson.findPerson(actualPerson) == null) {
            daoServicePerson.insert(actualPerson);
        }
        return Stream.of(Arguments.of(actualPerson));
    }


    private static Stream<Arguments> providePeopleForTestUpdate() {
        Person actualPerson = MockUtil.createTestPerson();
        if (daoServicePerson.findPerson(actualPerson) == null) {
            daoServicePerson.insert(actualPerson);
        }
        return Stream.of(Arguments.of(actualPerson));
    }


    private static Stream<Arguments> providePeopleForTestDelete() {
        Person actualPerson = MockUtil.createTestPerson();
        if (daoServicePerson.findPerson(actualPerson) == null) {
            daoServicePerson.insert(actualPerson);
        }
        return Stream.of(Arguments.of(actualPerson));
    }

    @ParameterizedTest
    @MethodSource("providePeopleForTestInsert")
    void testInsert(final Person person) throws SQLException {
        daoServicePerson.createPerson(person);
        Person expectedPerson = selectById(person.getId(),daoServicePerson.getConnection());

        assertNotNull(expectedPerson);
        assertAll(EQUALS_ALL_FIELDS,
                () -> assertEquals(expectedPerson.getName(), person.getName()),
                () -> assertEquals(expectedPerson.getSurname(),
                        person.getSurname())
        );
    }

    @ParameterizedTest
    @MethodSource("providePeopleForTestSelect")
    void testSelect(final Person person) {
        Person expectedPerson = (Person) daoServicePerson.findPerson(person);

        assertNotNull(expectedPerson);
        assertAll(EQUALS_ALL_FIELDS,
                () -> assertEquals(expectedPerson.getName(), person.getName()),
                () -> assertEquals(expectedPerson.getSurname(),
                        person.getSurname())
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
        assertAll(EQUALS_ALL_FIELDS,
                () -> assertEquals(expectedPerson.getName(), person.getName()),
                () -> assertEquals(expectedPerson.getSurname(),
                        person.getSurname())
        );
    }

    @ParameterizedTest
    @MethodSource("providePeopleForTestDelete")
    void testDelete(final Person person) throws SQLException {
        daoServicePerson.deletePerson(person);
        Person expectedPerson = selectById(person.getId(),daoServicePerson.getConnection());

        assertNull(expectedPerson);
    }

    @Test
    void testSelectAll() {
        Person actualPerson = MockUtil.createTestPerson();
        if (daoServicePerson.findPerson(actualPerson) == null) {
            daoServicePerson.insert(actualPerson);
        }
        List<Object> objectList = daoServicePerson.findAllPerson(Person.class);
        assertNotNull(objectList);

        assertNotNull(daoServicePerson.getStatement());

    }

    @AfterAll
    static void closeConnection() {
        JDBCConnection.closeConnection(daoServicePerson);
    }

}
