package org.example.service;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataPropertiesTest {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test";
    static final String USER = "sa";
    static final String PASS = "";
    @Test
    public void getConnectionTest() {
        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            Assert.fail("Expected IOException");
        } catch (ClassNotFoundException e) {
            Assert.fail("Expected ClassNotFoundException");
        }
        Assert.assertNotNull(connection);
    }
}