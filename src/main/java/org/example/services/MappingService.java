package org.example.services;

/**
 * @author Alexey
 * @version 1.0
 * интерфейс для конвертации.
 * @param <T>
 * @param <V>
 */
public interface MappingService<T, V> {
    /**
     * @param dto object for client view.
     * @return database entity.
     */
    T convertToEntity(V dto);

    /**
     * @param entity database entity.
     * @return object for client view.
     */
    V convertToDTO(T entity);
}
