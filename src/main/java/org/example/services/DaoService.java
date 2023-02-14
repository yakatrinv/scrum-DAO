package org.example.services;

import java.util.List;

public interface DaoService<T, V> {
    T create(T dto);

    T readById(V id);

    T update(T dto);

    void delete(T dto);

    void deleteById(V id);

    List<T> readAll();
}
