package org.example.dataSource;

import java.util.List;
import java.util.stream.Collectors;

import static org.example.entity.Util.CHAR_QUESTION;
import static org.example.entity.Util.DELETE_QUERY;
import static org.example.entity.Util.DELIMITER;
import static org.example.entity.Util.DELIMITER_UPD;
import static org.example.entity.Util.DELIM_UPD_END;
import static org.example.entity.Util.INSERT_QUERY;
import static org.example.entity.Util.SELECT_QUERY;
import static org.example.entity.Util.UPDATE_QUERY;

/**
 * @author Katerina
 * @version 1.0
 * класс с константными строками шаблонов заапросов.
 */
public final class DataQuery {
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

        String fields = String.join(DELIMITER, listFields);

        String values = listFields.stream()
                .map(s -> CHAR_QUESTION)
                .collect(Collectors.joining(DELIMITER));

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
                .map(s -> CHAR_QUESTION)
                .collect(Collectors.joining(DELIMITER));

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
        String idField =idFields.listIterator().next();
//        String idField = idFields.get(0);
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
