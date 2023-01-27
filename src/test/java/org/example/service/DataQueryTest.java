package org.example.service;

import org.example.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DataQueryTest {
    public Person person = Person.builder()
            .id(5)
            .name("nameP")
            .surname("surnameP")
            .build();

    @Test
    public void getInsertQuery() {
        String actualQuery = "INSERT INTO person (name,surname) VALUES (?,?);";
        String query = DataQuery.getInsertQuery(person);
        Assertions.assertEquals(query, actualQuery,
                "Текст запроса не совпадает");
    }

    @Test
    public void getSelectQuery() {
        String actualQuery = "SELECT * FROM person WHERE id = ?;";
        String query = DataQuery.getSelectQuery(person);
        Assertions.assertEquals(query, actualQuery,
                "Текст запроса не совпадает");
    }

    @Test
    public void getUpdateQuery() {
        String actualQuery
                = "UPDATE person SET name = ? ,surname = ? WHERE id = ?;";

        String query = DataQuery.getUpdateQuery(person);
        Assertions.assertEquals(query, actualQuery,
                "Текст запроса не совпадает");
    }

    @Test
    public void getDeleteQuery() {
        String actualQuery = "DELETE FROM person WHERE id = ?;";
        String query = DataQuery.getDeleteQuery(person);
        Assertions.assertEquals(query, actualQuery,
                "Текст запроса не совпадает");
    }
}
