package org.example.dao;

public final class TestConstant {
    public static final String SELECT_PERSON_BY_ID_SQL
            = "SELECT id, name, surname FROM person WHERE id = ?";
    public static final String INSERT_PERSON_SQL
            = "INSERT INTO person (name, surname) VALUES (?,?)";
    public static final String CLEAR_PERSON_TABLE_SQL = "DELETE FROM person";
    public static final String ID_COLUMN_TITLE = "id";
    public static final String NAME_COLUMN_TITLE = "name";
    public static final String SURNAME_COLUMN_TITLE = "surname";
    public static final int ZERO_INDEX = 0;
    public static final int FIRST_INDEX = 1;
    public static final int SECOND_INDEX = 2;
    public static final String CONRAD = "Conrad";
    public static final String POTTER = "Potter";
}
