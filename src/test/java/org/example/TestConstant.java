package org.example;

public final class TestConstant {
    /**
     * путь к базе данных.
     */
    public static final String TYPE_URL = "url";
    /**
     * название базы данных.
     */
    public static final String BASE_NAME = "database";
    /**
     * имя пользователя.
     */
    public static final String TYPE_USER = "user";
    /**
     * пароль пользователя.
     */
    public static final String TYPE_PASS = "password";
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
    public static final String EQUALS_ALL_FIELDS
            = "Test equality of all Person fields.";
    public static final String STR_ADD_UPD = "_Updated";
}
