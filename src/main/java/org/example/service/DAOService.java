package org.example.service;

import org.example.dao.IDAO;
import org.example.dataSource.JDBCConnection;
import org.example.dataSource.DataQuery;
import org.example.dataSource.ObjectService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @param <T>
 * @author Katerina
 * @version 1.0
 */
public class DAOService<T> implements IDAO<T> {
    /**
     * константа указывает на необходимость получения primary key
     * возвращается после его генерации при выполнении запроса.
     */
    public static final boolean RETURN_PRIMARY_KEY = true;
    /**
     * константа указывает, что получать primary key не нужно.
     */
    public static final boolean NOT_RETURN_PRIMARY_KEY = false;
    /**
     * connection.
     */
    private Connection connection = null;
    /**
     * statement.
     */
    private PreparedStatement statement = null;

    /**
     * set Connection.
     * @param connect current connection
     */
    public void setConnection(final Connection connect) {
        this.connection = connect;
    }

    /**
     * Get Connection.
     * @return connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * get Statement.
     * @return statement
     */
    public PreparedStatement getStatement() {
        return statement;
    }

    /**
     * @param t сущность, которая добавляеться в базу данных
     * @since 1.0
     */


    @Override
    public void insert(final T t) {
        String query = DataQuery.getInsertQuery(t);

        statement = JDBCConnection.initStatement(query,
                RETURN_PRIMARY_KEY,
                connection);
        JDBCConnection.setValues(ObjectService.getValuesWithoutPk(t),
                statement);
        JDBCConnection.commit(connection,
                statement);
        JDBCConnection.setId(t,
                statement);
    }

    /**
     * @param t сущность, которую ищем в базе данных по id
     * @since 1.0
     */
    @Override
    public void selectById(final T t) {
        String query = DataQuery.getSelectQuery(t);

        statement = JDBCConnection.initStatement(query,
                NOT_RETURN_PRIMARY_KEY,
                connection);
        JDBCConnection.setValues(ObjectService.getValuesPk(t),
                statement);
        ResultSet rs = JDBCConnection.execute(statement);
        if (rs != null) {
            JDBCConnection.printResult(rs, t);
        }
    }

    /**
     * @param t сущность, которая обновляется в базе данных, ищем по по id
     * @since 1.0
     */
    @Override
    public void update(final T t) {
        String query = DataQuery.getUpdateQuery(t);

        statement = JDBCConnection.initStatement(query,
                NOT_RETURN_PRIMARY_KEY,
                connection);
        JDBCConnection.setValues(ObjectService.getValuesWithPk(t),
                statement);
        JDBCConnection.commit(connection,
                statement);
    }

    /**
     * @param t сущность, которую удаляем в базе данных, ищем по по id
     * @since 1.0
     */
    @Override
    public void delete(final T t) {
        String query = DataQuery.getDeleteQuery(t);

        statement = JDBCConnection.initStatement(query,
                NOT_RETURN_PRIMARY_KEY,
                connection);
        JDBCConnection.setValues(ObjectService.getValuesPk(t),
                statement);
        JDBCConnection.commit(connection,
                statement);
    }
}
