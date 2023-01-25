package org.example.impl;

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
     * @param t объект сущности.
     *          поиск сущности в базе данных.
     */
    void select(T t);

    /**
     * @param t объект сущности.
     *          обновление сущности в базе данных.
     */
    void update(T t);

    /**
     * @param t объект сущности.
     *          удаление сущности в базе данных.
     */
    void delete(T t);
}
