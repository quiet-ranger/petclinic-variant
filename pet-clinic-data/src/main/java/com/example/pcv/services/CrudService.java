package com.example.pcv.services;

import java.util.Set;

public interface CrudService<T, ID> {

    Set<T> findAll();

    T findById(ID id);

    T save(ID id, T entity);

    void delete(T entity);

    void deleteById(ID id);
}
