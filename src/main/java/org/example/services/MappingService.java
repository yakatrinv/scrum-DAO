package org.example.services;

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
