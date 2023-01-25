package org.example.service;

import org.example.Person;
import org.junit.Assert;
import org.junit.Test;

public class DataQueryTest {
    public Person person = Person.builder()
            .id(5)
            .name("nameP")
            .surname("surnameP")
            .build();

    @Test
    public void getInsertQuery() {
        String actualQuery = "INSERT INTO person (name,surname) VALUE (?,?);";
        String query = DataQuery.getInsertQuery(person);
        Assert.assertEquals("Текст запроса не совпадает",query,actualQuery);
    }

    @Test
    public void getSelectQuery() {
        String actualQuery = "SELECT * FROM person WHERE id = ?;";
        String query = DataQuery.getSelectQuery(person);
        Assert.assertEquals("Текст запроса не совпадает",query,actualQuery);
    }

    @Test
    public void getUpdateQuery() {
        String actualQuery = "UPDATE person SET name = ?,surname = ? WHERE id = ?;";

        String query = DataQuery.getUpdateQuery(person);
        Assert.assertEquals("Текст запроса не совпадает",query,actualQuery);
    }

    @Test
    public void getDeleteQuery() {
        String actualQuery = "DELETE FROM person WHERE id = ?;";
        String query = DataQuery.getDeleteQuery(person);
        Assert.assertEquals("Текст запроса не совпадает",query,actualQuery);
    }
}
