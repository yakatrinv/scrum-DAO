package org.example.service;

import org.junit.Before;

import java.sql.*;

public class ConnectionDBTest {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test";
    static final String USER = "sa";
    static final String PASS = "";
    public Connection connection = null;
    public Statement statement = null;
    public String query = "INSERT INTO person (name,surname) VALUE (?,?);";
    public String quetySelect = "SELECT * FROM person WHERE id = ?;";

    @Before
    public void createTable() {
        query = "INSERT INTO personTest (id,name, surname) "
                + "VALUES (34,'nameP','surnameP')";
        quetySelect = "SELECT * FROM personTest";
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            String sqlQuery = "DROP TABLE personTest; "
                    + "CREATE TABLE  personTest"
                    + "(id INT not NULL, "
                    + "name VARCHAR (100), "
                    + "surname VARCHAR (100), "
                    + "PRIMARY KEY ( id ))";


            statement = connection.createStatement();
            try {
                statement.execute(sqlQuery);
                connection.commit();
            } catch (SQLException e) {

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
