package org.example.service;

import org.example.entity.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DataQueryTest {
    public static final String DIFF_QUERY = "Текст запроса не совпадает";
    public static final String INSERT_QUERY="INSERT INTO person (name,surname) VALUES (?,?);";
    public static final String UPDATE_QUERY="UPDATE person SET name = ? ,surname = ? WHERE id = ?;";
    public static final String DELETE_QUERY="DELETE FROM person WHERE id = ?;";
    public static final String SELECT_QUERY="SELECT * FROM person WHERE id = ?;";
    public static final String SELECT_ALL_QUERY="SELECT * FROM person;";
    public Person person = new Person();

    @Test
    public void getInsertQueryTest() {
        String query = DataQuery.getInsertQuery(person);
        Assertions.assertEquals(query, INSERT_QUERY,
                DIFF_QUERY);
    }

    @Test
    public void getSelectQueryTest() {
        String query = DataQuery.getSelectQuery(person);
        Assertions.assertEquals(query, SELECT_QUERY,
                DIFF_QUERY);
    }

    @Test
    public void getSelectAllQueryTest() {
        String query = DataQuery.getSelectAllQuery(Person.class);
        Assertions.assertEquals(query, SELECT_ALL_QUERY,
                DIFF_QUERY);
    }

    @Test
    public void getUpdateQueryTest() {
        String query = DataQuery.getUpdateQuery(person);
        Assertions.assertEquals(query, UPDATE_QUERY,
                DIFF_QUERY);
    }

    @Test
    public void getDeleteQueryTest() {
        String query = DataQuery.getDeleteQuery(person);
        Assertions.assertEquals(query, DELETE_QUERY,
                DIFF_QUERY);
    }
}
