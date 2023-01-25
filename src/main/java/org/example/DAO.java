package org.example;

import org.example.impl.IDAO;
import org.example.service.ConnectionDB;
import org.example.service.DataQuery;
import org.example.service.ObjectService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @param <T>
 * @author Katerina
 * @version 1.0
 */
public class DAO<T> implements IDAO<T> {
    /**
     * константа указывает на необходимость получения primary key
     * возвращается после его генерации при выполнении запроса.
     */
    public static final boolean RETURN_PRIMARY_KEY = true;
    /**
     * константа указывает, что получать primary key не нужно.
     */
    public static final boolean NOT_RETURN_PRIMARY_KEY = false;

    private Connection connection = null;
    private PreparedStatement statement = null;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

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

        statement = ConnectionDB.setConnection(query,RETURN_PRIMARY_KEY,connection);
        ConnectionDB.setValues(ObjectService.getValuesWithoutPk(t),statement);
        ConnectionDB.commit(connection,statement);
        ConnectionDB.setId(t,statement);
    }

    /**
     * @param t сущность, которую ищем в базе данных по id
     * @since 1.0
     */
    @Override
    public void select(final T t) {
        String query = DataQuery.getSelectQuery(t);

        statement = ConnectionDB.setConnection(query,NOT_RETURN_PRIMARY_KEY,connection);
        ConnectionDB.setValues(ObjectService.getValuesPk(t),statement);
        ResultSet rs = ConnectionDB.execute(statement);
        if (rs != null) {
            ConnectionDB.printResult(rs, t);
        }
    }

    /**
     * @param t сущность, которая обновляется в базе данных, ищем по по id
     * @since 1.0
     */
    @Override
    public void update(final T t) {
        String query = DataQuery.getUpdateQuery(t);

        statement = ConnectionDB.setConnection(query,NOT_RETURN_PRIMARY_KEY,connection);
        ConnectionDB.setValues(ObjectService.getValuesWithPk(t),statement);
        ConnectionDB.commit(connection,statement);
    }

    /**
     * @param t сущность, которую удаляем в базе данных, ищем по по id
     * @since 1.0
     */
    @Override
    public void delete(final T t) {
        String query = DataQuery.getDeleteQuery(t);

        statement = ConnectionDB.setConnection(query,NOT_RETURN_PRIMARY_KEY,connection);
        ConnectionDB.setValues(ObjectService.getValuesPk(t),statement);
        ConnectionDB.commit(connection,statement);
    }
}
