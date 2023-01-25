package org.example.service;

import org.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;

/**
 * class for work with connection jbdc.
 */
public class ConnectionDB {
    /**
     * return generate key after query.
     */
    public static final int RETURN_KEY = Statement.RETURN_GENERATED_KEYS;
    /**
     * first index for set value in prepared statement.
     */
    public static final int FIRST_INDEX = 1;

    /**
     * initialisation current connection.
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DataProperties.getConnection();
            if (conn != null) {
                conn.setAutoCommit(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * @param query SQL query for inintialisation
     * @param pKey  true - return genereted key, false - not return
     *              initialisation statements in current connection.
     */
    public static PreparedStatement setConnection(final String query,
                                                  final boolean pKey,
                                                  final Connection conn) {
        PreparedStatement statement1 = null;
        try {
            if (conn != null) {

                if (pKey) {
                    statement1 = conn.prepareStatement(query, RETURN_KEY);
                } else {
                    statement1 = conn.prepareStatement(query);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement1;
    }

    /**
     * фиксирует  транзакцию.
     */
    public static void commit(Connection conn, PreparedStatement stmt) {
        try {
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    /**
     * выполняет запрос из prepared statement.
     *
     * @return результат выполнения запроса
     */
    public static ResultSet execute(PreparedStatement statement) {
        try {
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * закрывает открытые соединения.
     */
    public static <T> void closeConnection(DAO<T> dao) {
        try {
            if (dao.getStatement() != null) {
                dao.getStatement().close();
            }
            if (dao.getConnection() != null) {
                dao.getConnection().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Устанавлиавает перечень значений в prepared statement.
     *
     * @param values список значений для установки в prepared statement
     * @param <T>    тип сущности
     */
    public static <T> void setValues(final List<T> values, PreparedStatement statement1) {
        int i = 1;
        for (Object value : values) {
            setValue(i, value, statement1);
            i++;
        }
    }

    /**
     * устанавливает значение в prepared statement по индексу.
     *
     * @param index индекс, в который вносится значение в запрос,
     *              который установлен в prepared statement
     * @param value значение, котороевносится значение в запрос,
     *              который установлен в prepared statement
     * @param <T>   тип сущности
     */
    private static <T> void setValue(final int index,
                                     final T value,
                                     PreparedStatement statement1) {
        try {
            if (value.getClass().equals(Byte.class)) {
                statement1.setByte(index, (byte) value);
            } else if (value.getClass().equals(Short.class)) {
                statement1.setShort(index, (short) value);
            } else if (value.getClass().equals(Integer.class)) {
                statement1.setInt(index, (int) value);
            } else if (value.getClass().equals(Long.class)) {
                statement1.setLong(index, (long) value);
            } else if (value.getClass().equals(Float.class)) {
                statement1.setFloat(index, (float) value);
            } else if (value.getClass().equals(Double.class)) {
                statement1.setDouble(index, (double) value);
            } else if (value.getClass().equals(Boolean.class)) {
                statement1.setBoolean(index, (boolean) value);
            } else {
                statement1.setString(index, (String) value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * устанавливает id в указанный объект класса.
     *
     * @param t   объект сущности
     * @param <T> тип сущности
     */
    public static <T> void setId(final T t, PreparedStatement stmt) {
        ResultSet rs;
        try {
            rs = stmt.getGeneratedKeys();
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
        List<String> fields = ObjectService.getFieldsWithoutPk(t);
        try {
            while (rs.next()) {
                System.out.println();
                for (String field : fields) {
                    String nameColumn = rs.getString(field);
                    System.out.print(nameColumn + "\t");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
