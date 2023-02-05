package org.example.service;

public interface MappingService<T, V> {
    T convertToEntity(V dto);
    V convertToDTO(T entity);
}
