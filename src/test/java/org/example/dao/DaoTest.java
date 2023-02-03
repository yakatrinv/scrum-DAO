package org.example.dao;

import org.example.entity.Person;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Stream;

import static org.example.dao.MockUtil.getConnection;
import static org.example.dao.MockUtil.selectById;
import static org.example.dao.TestConstant.CARRIAGE_RETURN;
import static org.example.dao.TestConstant.CREATE_PERSON_TABLE_SQL;
import static org.example.dao.TestConstant.DELETE_PERSON_TABLE_SQL;
import static org.example.dao.TestConstant.ORIGINAL_OUT;
import static org.example.dao.TestConstant.STRING_RETURN;
import static org.example.dao.TestConstant.TABULATION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
final class DaoTest {
    private static Connection connection;
    private static DAOService<Person> daoServicePerson;
    private static ByteArrayOutputStream out;

    @BeforeAll
    static void createPersonData() throws SQLException {
        connection = getConnection();
        connection.setAutoCommit(false);
        daoServicePerson = new DAOServicePerson();
        daoServicePerson.setConnection(connection);
        try (Statement statement = connection.createStatement()) {
            statement.execute(CREATE_PERSON_TABLE_SQL);
            connection.commit();
        }
    }

    @BeforeEach
    void setOutStream() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    private static Stream<Arguments> providePeopleForTestInsert() {
        return Stream.of(Arguments.of(new Person(1, "Conrad", "Potter")),
                Arguments.of(new Person(2, "Kathy", "Kirkham")),
                Arguments.of(new Person(3, "Alvin", "Greenwood")),
                Arguments.of(new Person(4, "Kelly", "Felderman")),
                Arguments.of(new Person(5, "Edward", "Williamson")),
                Arguments.of(new Person(6, "Maria", "Webb")),
                Arguments.of(new Person(7, "Stanley", "Webb")),
                Arguments.of(new Person(8, "Alice", "Kingston")),
                Arguments.of(new Person(9, "Bradley", "Hagman")),
                Arguments.of(new Person(10, "Danielle", "Sadler")));
    }

    private static Stream<Arguments> providePeopleForTestSelect() {
        return Stream.of(
                Arguments.of(new Person(1, "Conrad", "Potter"),
                        CARRIAGE_RETURN + STRING_RETURN + "Conrad" + TABULATION
                                + "Potter" + TABULATION),
                Arguments.of(new Person(2, "Kathy", "Kirkham"),
                        CARRIAGE_RETURN + STRING_RETURN + "Kathy" + TABULATION
                                + "Kirkham" + TABULATION),
                Arguments.of(new Person(3, "Alvin", "Greenwood"),
                        CARRIAGE_RETURN + STRING_RETURN + "Alvin" + TABULATION
                                + "Greenwood" + TABULATION),
                Arguments.of(new Person(4, "Kelly", "Felderman"),
                        CARRIAGE_RETURN + STRING_RETURN + "Kelly" + TABULATION
                                + "Felderman" + TABULATION),
                Arguments.of(new Person(5, "Edward", "Williamson"),
                        CARRIAGE_RETURN + STRING_RETURN + "Edward" + TABULATION
                                + "Williamson" + TABULATION),
                Arguments.of(new Person(6, "Maria", "Webb"),
                        CARRIAGE_RETURN + STRING_RETURN + "Maria" + TABULATION
                                + "Webb" + TABULATION),
                Arguments.of(new Person(7, "Stanley", "Webb"),
                        CARRIAGE_RETURN + STRING_RETURN + "Stanley" + TABULATION
                                + "Webb" + TABULATION),
                Arguments.of(new Person(8, "Alice", "Kingston"),
                        CARRIAGE_RETURN + STRING_RETURN + "Alice" + TABULATION
                                + "Kingston" + TABULATION),
                Arguments.of(new Person(9, "Bradley", "Hagman"),
                        CARRIAGE_RETURN + STRING_RETURN + "Bradley" + TABULATION
                                + "Hagman" + TABULATION),
                Arguments.of(new Person(10, "Danielle", "Sadler"),
                        CARRIAGE_RETURN + STRING_RETURN + "Danielle"
                                + TABULATION + "Sadler" + TABULATION));
    }

    private static Stream<Arguments> providePeopleForTestUpdate() {
        return Stream.of(
                Arguments.of(new Person(1, "Conrad", "Potter"),
                        new Person(1, "CONRAD", "POTTER")),
                Arguments.of(new Person(2, "Kathy", "Kirkham"),
                        new Person(2, "KATHY", "KIRKHAM")),
                Arguments.of(new Person(3, "Alvin", "Greenwood"),
                        new Person(3, "ALVIN", "GREENWOOD")),
                Arguments.of(new Person(4, "Kelly", "Felderman"),
                        new Person(4, "KELLY", "FELDERMAN")),
                Arguments.of(new Person(5, "Edward", "Williamson"),
                        new Person(5, "EDWARD", "WILLIAMSON")),
                Arguments.of(new Person(6, "Maria", "Webb"),
                        new Person(6, "MARIA", "WEBB")),
                Arguments.of(new Person(7, "Stanley", "Webb"),
                        new Person(7, "STANLEY", "WEBB")),
                Arguments.of(new Person(8, "Alice", "Kingston"),
                        new Person(8, "ALICE", "KINGSTON")),
                Arguments.of(new Person(9, "Bradley", "Hagman"),
                        new Person(9, "BRADLEY", "HAGMAN")),
                Arguments.of(new Person(10, "Danielle", "Sadler"),
                        new Person(10, "DANIELLE", "SADLER")));
    }

    private static Stream<Arguments> providePeopleForTestDelete() {
        return Stream.of(
                Arguments.of(new Person(1, null, null)),
                Arguments.of(new Person(2, null, null)),
                Arguments.of(new Person(3, null, null)),
                Arguments.of(new Person(4, null, null)),
                Arguments.of(new Person(5, null, null)),
                Arguments.of(new Person(6, null, null)),
                Arguments.of(new Person(7, null, null)),
                Arguments.of(new Person(8, null, null)),
                Arguments.of(new Person(9, null, null)),
                Arguments.of(new Person(10, null, null))
        );
    }

    @ParameterizedTest
    @MethodSource("providePeopleForTestInsert")
    @Order(1)
    void testInsert(final Person person) throws SQLException {
        daoServicePerson.insert(person);
        assertEquals(person, selectById(person.getId(), connection));
    }

    @ParameterizedTest
    @MethodSource("providePeopleForTestSelect")
    @Order(2)
    void testSelect(final Person person, final String printedPerson) {
        daoServicePerson.selectById(person);
        assertEquals(printedPerson, out.toString());
    }

    @ParameterizedTest
    @MethodSource("providePeopleForTestUpdate")
    @Order(3)
    void testUpdate(final Person person, final Person updatedPerson)
            throws SQLException {
        person.setName(person.getName().toUpperCase());
        person.setSurname(person.getSurname().toUpperCase());
        daoServicePerson.update(updatedPerson);
        assertEquals(person, selectById(person.getId(), connection));
    }

    @ParameterizedTest
    @MethodSource("providePeopleForTestDelete")
    @Order(4)
    void testDelete(final Person person) throws SQLException {
        daoServicePerson.deleteById(person);
        assertNull(selectById(person.getId(), connection));
    }

    @AfterAll
    static void deletePersonData() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(DELETE_PERSON_TABLE_SQL);
        } finally {
            connection.close();
        }
    }

    @AfterAll
    static void restoreInitialOutStream() {
        System.setOut(ORIGINAL_OUT);
    }
}
