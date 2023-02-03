package org.example.dataSource;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Katerina
 * @version 1.0
 * класс с константными строками шаблонов заапросов.
 */
public final class DataQuery {
    /**
     * символ для указания параметра в запросе
     * используется при формировании строки запроса.
     */
    public static final String CHAR_QUESTION = "?";
    /**
     * SQL шаблон для добавления записи в базу данных
     * используется при формировании строки запроса.
     */
    public static final String INSERT_QUERY
            = "INSERT INTO %s (%s) VALUES (%s);";
    /**
     * SQL шаблон для выбора записей в базу данных
     * используется при формировании строки запроса.
     */
    public static final String SELECT_QUERY = "SELECT * FROM %s WHERE %s = %s;";
    /**
     * SQL шаблон для обновления записи в базу данных
     * используется при формировании строки запроса.
     */
    public static final String UPDATE_QUERY = "UPDATE %s SET %s WHERE %s = ?;";
    /**
     * SQL шаблон для удаления записи в базу данных
     * используется при формировании строки запроса.
     */
    public static final String DELETE_QUERY = "DELETE FROM %s WHERE %s = ?;";
    /**
     * delimiter for columns.
     */
    public static final String DELIMITER = ",";
    /**
     * delimiter for columns in update query.
     */
    public static final String DELIMITER_UPD = " = ? ,";
    /**
     * delimiter for columns in update query in the end.
     */
    public static final String DELIM_UPD_END = " = ?";

    /**
     * конструктор по умолчанию.
     */
    private DataQuery() {
    }

    /**
     * @param t   данные объекта класса
     * @param <T> тип сущности
     * @return возвращает строку с SQL запросом
     * на добавдение записи в базе данных.
     */
    public static <T> String getInsertQuery(final T t) {
        String nameTable = ObjectService.getNameTable(t);
        List<String> listFields = ObjectService.getFieldsWithoutPk(t);

        String fields = String.join(",", listFields);

        String values = listFields.stream()
                .map(s -> DataQuery.CHAR_QUESTION)
                .collect(Collectors.joining(","));

        return String.format(INSERT_QUERY, nameTable, fields, values);
    }

    /**
     * @param t   данные объекта класса
     * @param <T> тип сущности
     * @return возвращает строку с SQL запросом
     * на поиск записи в базе данных.
     */
    public static <T> String getSelectQuery(final T t) {
        String nameTable = ObjectService.getNameTable(t);
        List<String> listFields = ObjectService.getPkField(t);

        String fields = String.join(DELIMITER, listFields);

        String values = listFields.stream()
                .map(s -> DataQuery.CHAR_QUESTION)
                .collect(Collectors.joining(","));

        return String.format(SELECT_QUERY, nameTable, fields, values);
    }

    /**
     * @param t   данные объекта класса
     * @param <T> тип сущности
     * @return возвращает строку с SQL запросом
     * на обновление записи в базе данных.
     */
    public static <T> String getUpdateQuery(final T t) {
        String nameTable = ObjectService.getNameTable(t);
        List<String> listFields = ObjectService.getFieldsWithoutPk(t);
        List<String> idFields = ObjectService.getPkField(t);

        String fields = String.join(DELIMITER_UPD, listFields) + DELIM_UPD_END;
        String idField = idFields.get(0);
        return String.format(UPDATE_QUERY, nameTable, fields, idField);
    }

    /**
     * @param t   данные объекта класса
     * @param <T> тип сущности
     * @return возвращает строку с SQL запросом
     * на удаление записи в базе данных.
     */
    public static <T> String getDeleteQuery(final T t) {
        String nameTable = ObjectService.getNameTable(t);
        List<String> listFields = ObjectService.getPkField(t);

        String fields = String.join(DELIMITER, listFields);

        return String.format(DELETE_QUERY, nameTable, fields);
    }

}
