package org.example.reposotiry;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class DataPropertiesTest {
    @Test
    public void getConnection() throws SQLException {
        Connection expConnection = DataProperties.getConnection();
        Assertions.assertNotNull(expConnection);
        expConnection.close();
    }
}
