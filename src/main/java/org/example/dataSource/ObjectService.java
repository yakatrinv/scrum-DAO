package org.example.dataSource;

import org.example.anno.MyColumn;
import org.example.anno.MyTable;
import org.example.anno.PrimaryKey;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Katerina
 * @version 1.0
 * класс для получения данных
 * об объектах, полях, данных аннотаций полей.
 */
public final class ObjectService {
    /**
     * тип - аннотация MyTable.
     */
    public static final Class<MyTable> ANNO_MY_TABLE = MyTable.class;
    /**
     * тип - аннотация MyColumn.
     */
    public static final Class<MyColumn> ANNO_MY_COLUMN = MyColumn.class;
    /**
     * тип - аннотация PrimaryKey.
     */
    public static final Class<PrimaryKey> ANNO_PRIMARY_KEY = PrimaryKey.class;
    /**
     * константа указывает на добавление
     * полей с аннотацией MyColumn.
     */
    public static final boolean ADD_FIELDS = true;
    /**
     * константа указывает отказ от добавления
     * полей с аннотацией MyColumn.
     */
    public static final boolean NO_ADD_FIELDS = false;
    /**
     * константа указывает на добавление
     * полей с аннотацией PrimaryKey.
     */
    public static final boolean ADD_PK = true;
    /**
     * константа указывает на отказ от добавления
     * полей с аннотацией PrimaryKey.
     */
    public static final boolean NO_ADD_PK = false;

    /**
     * конструктор по умолчанию.
     */
    private ObjectService() {
    }

    /**
     * Получает имя таблицы в базе данных по аннотации MyTable.
     *
     * @param object экземпляр класс
     * @return имя таблицы
     */
    public static String getNameTable(final Object object) {
        return object.getClass().getAnnotation(ANNO_MY_TABLE).name();
    }

    /**
     * Получает перечень полей объекта с учетом указателей.
     *
     * @param t         экземпляр класс
     * @param addFields указатель добавления полей с аннотацией MyColumn
     * @param addPk     указатель добавления полей с аннотацией PrimaryKey
     * @return list строк имен полей
     */
    public static List<String> getFields(final Object t,
                                         final boolean addFields,
                                         final boolean addPk) {
        List<String> resultList = new ArrayList<>();
        Field fieldPk = null;

        Field[] fieldsArray = t.getClass().getDeclaredFields();
        for (Field field : fieldsArray) {
            if (addFields && field.isAnnotationPresent(ANNO_MY_COLUMN)
                    && !field.isAnnotationPresent(ANNO_PRIMARY_KEY)) {
                field.setAccessible(true);
                resultList.add(field.getAnnotation(ANNO_MY_COLUMN).name());
                field.setAccessible(false);
            }

            if (addPk && field.isAnnotationPresent(ANNO_PRIMARY_KEY)) {
                field.setAccessible(true);
                fieldPk = field;
                field.setAccessible(false);
            }
        }

        if (addPk && fieldPk != null) {
            String namePk = fieldPk.getAnnotation(ANNO_PRIMARY_KEY).name();
            resultList.add(namePk);
        }

        return resultList;
    }

    /**
     * Получает перечень полей объекта без поля PrimaryKey.
     *
     * @param t экземпляр класс
     * @return list строк имен полей
     */
    public static List<String> getFieldsWithoutPk(final Object t) {
        return getFields(t, ADD_FIELDS, NO_ADD_PK);
    }

    /**
     * Получает перечень полей объекта с аннотацией PrimaryKey.
     *
     * @param t экземпляр класс
     * @return list строк имен полей
     */
    public static List<String> getPkField(final Object t) {
        return getFields(t, NO_ADD_FIELDS, ADD_PK);
    }

    /**
     * Получает перечень значений полей объекта с учетом указателей.
     *
     * @param t         экземпляр класс
     * @param addFields указатель добавления полей с аннотацией MyColumn
     * @param addPk     указатель добавления полей с аннотацией PrimaryKey
     * @return list значений полей объекта
     */
    public static List<Object> getValues(final Object t,
                                         final boolean addFields,
                                         final boolean addPk) {
        List<Object> resultList = new ArrayList<>();
        Object fieldPk = null;

        Field[] fieldsArray = t.getClass().getDeclaredFields();
        for (Field field : fieldsArray) {
            if (addFields && field.isAnnotationPresent(ANNO_MY_COLUMN)
                    && !field.isAnnotationPresent(ANNO_PRIMARY_KEY)) {
                field.setAccessible(true);
                try {
                    resultList.add(field.get(t));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                field.setAccessible(false);
            }

            if (addPk && field.isAnnotationPresent(ANNO_PRIMARY_KEY)) {
                field.setAccessible(true);
                try {
                    fieldPk = field.get(t);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                field.setAccessible(false);
            }
        }

        if (addPk && fieldPk != null) {
            resultList.add(fieldPk);
        }

        return resultList;
    }

    /**
     * Получает перечень значений всех полей объекта.
     *
     * @param t экземпляр класс
     * @return list значений полей объекта
     */
    public static List<Object> getValuesWithPk(final Object t) {
        return getValues(t, ADD_FIELDS, ADD_PK);
    }

    /**
     * Получает перечень значений полей объекта
     * без полей с аннотацией PrimaryKey.
     *
     * @param t экземпляр класс
     * @return list значений полей объекта
     */
    public static List<Object> getValuesWithoutPk(final Object t) {
        return getValues(t, ADD_FIELDS, NO_ADD_PK);
    }

    /**
     * Получает перечень значений полей объекта
     * с аннотацией PrimaryKey.
     *
     * @param t экземпляр класс
     * @return list значений полей объекта
     */
    public static List<Object> getValuesPk(final Object t) {
        return getValues(t, NO_ADD_FIELDS, ADD_PK);
    }

    /**
     * Устанавливает значение поля с аннотацией PrimaryKey.
     *
     * @param t     экземпляр класс
     * @param value значение
     */
    public static void setId(final int value, final Object t) {
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ANNO_PRIMARY_KEY)) {
                try {
                    field.setAccessible(true);
                    field.set(t, value);
                    field.setAccessible(false);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
