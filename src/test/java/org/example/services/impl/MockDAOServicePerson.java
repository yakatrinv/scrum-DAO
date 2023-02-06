package org.example.services.impl;

import org.example.dao.DAOServicePerson;

import java.sql.Connection;
import java.sql.SQLException;

public final class MockDAOServicePerson extends DAOServicePerson {
    /**
     * mock connection.
     */
    private Connection connection = MockDataProperties.getConnection();

    @Override
    public void setConnection(final Connection connect) {
        try {
            if (connection.isClosed()) {
                connection = MockDataProperties.getConnection();
            }
        } catch (SQLException newE) {
            newE.printStackTrace();
        }
        super.setConnection(connection);
    }
}
