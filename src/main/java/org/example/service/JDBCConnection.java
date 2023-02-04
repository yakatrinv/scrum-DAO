package org.example.service;

import org.example.dao.DAOService;
import org.example.dataSource.ObjectService;
import org.example.entity.DataProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;

import static org.example.entity.DataProperties.PASS;
import static org.example.entity.DataProperties.URL;
import static org.example.entity.DataProperties.USER;

/**
 * class for work with connection jbdc.
 */
public final class JDBCConnection {
    /**
     * return generate key after query.
     */
    public static final int RETURN_KEY = Statement.RETURN_GENERATED_KEYS;

    /**
     * first index for set value in prepared statement.
     */
    public static final int FIRST_INDEX = 1;
    /**
     * tabulation.
     */
    public static final String CHAR_TAB = "\t";

    /**
     * private constructor.
     */
    private JDBCConnection() {
    }

    /**
     * initialisation current connection.
     *
     * @return 'connection'
     */
    public static Connection initConnection() {
        Connection connection = null;
        try {
            connection = DataProperties.getConnection();
            if (connection != null) {
                connection.setAutoCommit(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * @param query      SQL query for inintialisation
     * @param pKey       true - return genereted key, false - not return
     *                   initialisation statements in current connection.
     * @param connection current connection
     * @return 'statement'
     */
    public static PreparedStatement initStatement(final String query,
                                                  final boolean pKey,
                                                  final Connection connection) {
        PreparedStatement statement = null;
        try {
            if (connection != null) {

                if (pKey) {
                    statement = connection.prepareStatement(query, RETURN_KEY);
                } else {
                    statement = connection.prepareStatement(query);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    /**
     * фиксирует  транзакцию.
     *
     * @param statement  current statement
     * @param connection current connection
     */
    public static void commit(final Connection connection,
                              final PreparedStatement statement) {
        try {
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    /**
     * выполняет запрос из prepared statement.
     *
     * @param statement current statement
     * @return результат выполнения запроса
     */
    public static ResultSet execute(final PreparedStatement statement) {
        try {
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * закрывает открытые соединения.
     *
     * @param daoService DAO
     * @param <T>        тип сущности
     */
    public static <T> void closeConnection(final DAOService<T> daoService) {
        try {
            if (daoService.getStatement() != null) {
                daoService.getStatement().close();
            }
            if (daoService.getConnection() != null) {
                daoService.getConnection().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Устанавлиавает перечень значений в prepared statement.
     *
     * @param values    список значений для установки в prepared statement
     * @param <T>       тип сущности
     * @param statement current statement
     */
    public static <T> void setValues(final List<T> values,
                                     final PreparedStatement statement) {
        int i = 1;
        for (Object value : values) {
            setValue(i, value, statement);
            i++;
        }
    }

    /**
     * устанавливает значение в prepared statement по индексу.
     *
     * @param index     индекс, в который вносится значение в запрос,
     *                  который установлен в prepared statement
     * @param value     значение, котороевносится значение в запрос,
     *                  который установлен в prepared statement
     * @param <T>       тип сущности
     * @param statement current statement
     */
    private static <T> void setValue(final int index,
                                     final T value,
                                     final PreparedStatement statement) {
        try {
            if (value.getClass().equals(Byte.class)) {
                statement.setByte(index, (byte) value);
            } else if (value.getClass().equals(Short.class)) {
                statement.setShort(index, (short) value);
            } else if (value.getClass().equals(Integer.class)) {
                statement.setInt(index, (int) value);
            } else if (value.getClass().equals(Long.class)) {
                statement.setLong(index, (long) value);
            } else if (value.getClass().equals(Float.class)) {
                statement.setFloat(index, (float) value);
            } else if (value.getClass().equals(Double.class)) {
                statement.setDouble(index, (double) value);
            } else if (value.getClass().equals(Boolean.class)) {
                statement.setBoolean(index, (boolean) value);
            } else {
                statement.setString(index, (String) value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * устанавливает id в указанный объект класса.
     *
     * @param t         объект сущности
     * @param <T>       тип сущности
     * @param statement current statement
     */
    public static <T> void setId(final T t, final PreparedStatement statement) {
        ResultSet rs;
        try {
            rs = statement.getGeneratedKeys();
            if (rs.next()) {
                ObjectService.setId(rs.getInt(FIRST_INDEX), t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * @param rs  полученные результаты запроса
     * @param t   данные объекта класса
     * @param <T> тип сущности
     *            Вывод на печать данных о полученном результате
     *            перечень полей получается из данных об объекте.
     */
    public static <T> void printResult(final ResultSet rs, final T t) {
        print(rs, t.getClass());
    }

    /**
     * @param rs полученные результаты запроса
     * @param t  данные объекта класса
     *           Вывод на печать данных о полученном результате
     *           перечень полей получается из данных об объекте.
     */
    public static void printClassResult(final ResultSet rs, final Class<?> t) {
        print(rs, t);
    }

    /**
     * @param rs полученные результаты запроса
     * @param t  данные объекта класса
     *           Вывод на печать данных о полученном результате
     *           перечень полей получается из данных об объекте.
     */
    private static void print(final ResultSet rs, final Class<?> t) {
        List<String> fields = ObjectService.getAllFields(t);
        try {
            while (rs.next()) {
                System.out.println();
                for (String field : fields) {
                    String nameColumn = rs.getString(field);
                    System.out.print(nameColumn + CHAR_TAB);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
