package org.example.dao;

import java.util.List;

/**
 * @param <T> тип сущности.
 * @author Katerina
 * @version 1.0
 * интерфейс для работы с опреациями crud.
 */
public interface IDAO<T> {
    /**
     * @param t объект сущности.
     *          добавление сущности в базу данных.
     */
    void insert(T t);

    /**
     * @param t объект сущности
     * @return найденная сущность.
     *          поиск сущности в базе данных.
     */
    Object selectById(T t);

    /**
     *  поиск всех сущностей в базе данных.
     * @param t класс
     * @return список найденных сущностей.
     */
    List<Object> selectAll(Class<?> t);

    /**
     * @param t объект сущности.
     *          обновление сущности в базе данных.
     */
    void update(T t);

    /**
     * @param t объект сущности.
     *          удаление сущности в базе данных.
     */
    void deleteById(T t);
}
