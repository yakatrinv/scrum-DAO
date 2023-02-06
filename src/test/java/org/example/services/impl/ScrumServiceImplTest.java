package org.example.services.impl;

import org.example.entity.Person;
import org.example.services.ScrumSwrvice;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.reflection.FieldSetter;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.example.dao.DaoServiceTest.EQUALS_ALL_FIELDS;
import static org.example.dao.DaoServiceTest.STR_ADD_UPD;
import static org.example.dao.MockUtil.clearPersonTable;
import static org.example.dao.MockUtil.createTestPerson;
import static org.example.dao.MockUtil.insertPerson;
import static org.example.dao.MockUtil.selectById;
import static org.example.dao.TestConstant.ZERO_INDEX;
import static org.example.services.impl.MockDataProperties.getConnection;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public final class ScrumServiceImplTest {

    private static final MockDAOServicePerson MOCK_DAO_SERVICE_PERSON
            = new MockDAOServicePerson();
    private static final ScrumSwrvice SCRUM_SERVICE = new ScrumServiceImpl();

    @BeforeAll
    static void setUp() throws NoSuchFieldException {
        FieldSetter.setField(SCRUM_SERVICE,
                SCRUM_SERVICE.getClass().getDeclaredField("daoServicePerson"),
                MOCK_DAO_SERVICE_PERSON);
    }

    @Test
    void create() throws SQLException {
        Person expectedPerson = createTestPerson();
        SCRUM_SERVICE.create(expectedPerson);
        Person actualPerson = selectById(expectedPerson.getId(),
                getConnection());
        assertAll(EQUALS_ALL_FIELDS,
                () -> assertEquals(expectedPerson.getId(),
                        actualPerson.getId()),
                () -> assertEquals(expectedPerson.getName(),
                        actualPerson.getName()),
                () -> assertEquals(expectedPerson.getSurname(),
                        actualPerson.getSurname())
        );
    }

    @Test
    void update() throws SQLException {
        Person person = createTestPerson();
        insertPerson(person, Objects.requireNonNull(getConnection()));
        person.setName(person.getName() + STR_ADD_UPD);
        person.setSurname(person.getSurname() + STR_ADD_UPD);
        SCRUM_SERVICE.update(person);
        Person actualPerson = selectById(person.getId(), getConnection());
        assertNotNull(actualPerson);
        assertAll(EQUALS_ALL_FIELDS,
                () -> assertEquals(person.getId(), actualPerson.getId()),
                () -> assertEquals(person.getName(), actualPerson.getName()),
                () -> assertEquals(person.getSurname(),
                        actualPerson.getSurname())
        );
    }

    @Test
    void delete() throws SQLException {
        Person person = createTestPerson();
        insertPerson(person, Objects.requireNonNull(getConnection()));
        SCRUM_SERVICE.delete(person);
        Person actualPerson = selectById(person.getId(), getConnection());
        assertNull(actualPerson);
    }

    @Test
    void findById() throws SQLException {
        Person actualPerson = createTestPerson();
        insertPerson(actualPerson, Objects.requireNonNull(getConnection()));
        Person expectedPerson = (Person) SCRUM_SERVICE.findById(actualPerson);
        assertAll(EQUALS_ALL_FIELDS,
                () -> assertEquals(expectedPerson.getId(),
                        actualPerson.getId()),
                () -> assertEquals(expectedPerson.getName(),
                        actualPerson.getName()),
                () -> assertEquals(expectedPerson.getSurname(),
                        actualPerson.getSurname())
        );
    }

    @Test
    void findAll() throws SQLException {
        List<Person> actualPeople = List.of(createTestPerson());
        for (Person person : actualPeople) {
            insertPerson(person, Objects.requireNonNull(getConnection()));
        }
        List<Person> expectedPeople = SCRUM_SERVICE.findAll(Person.class)
                .stream().map(x -> (Person) x).collect(Collectors.toList());
        assertAll(EQUALS_ALL_FIELDS,
                () -> assertEquals(expectedPeople.get(ZERO_INDEX).getId(),
                        actualPeople.get(ZERO_INDEX).getId()),
                () -> assertEquals(expectedPeople.get(ZERO_INDEX).getName(),
                        actualPeople.get(ZERO_INDEX).getName()),
                () -> assertEquals(expectedPeople.get(ZERO_INDEX).getSurname(),
                        actualPeople.get(ZERO_INDEX).getSurname())
        );
    }

    @AfterEach
    void clearTable() throws SQLException {
        clearPersonTable(Objects.requireNonNull(getConnection()));
    }
}