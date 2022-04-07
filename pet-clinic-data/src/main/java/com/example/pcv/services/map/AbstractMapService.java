package com.example.pcv.services.map;

import com.example.pcv.services.CrudService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractMapService<T, ID> implements CrudService<T, ID> {

    protected Map<ID, T> map = new HashMap<>();

    @Override
    public Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    @Override
    public T findById(ID id) {
        try {
            return this.map.get(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public T save(ID id, T entity) {
        map.put(id, entity);
        return entity;
    }

    @Override
    public void delete(T entity) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(entity));
    }

    @Override
    public void deleteById(ID id) {
        map.remove(id);
    }

}
