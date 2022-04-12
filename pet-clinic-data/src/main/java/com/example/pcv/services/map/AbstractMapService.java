package com.example.pcv.services.map;

import com.example.pcv.model.BaseEntity;
import com.example.pcv.services.CrudService;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> implements CrudService<T, ID> {

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
    public T save(T entity) {
        if (entity != null) {
            if (entity.getId() == null) {
                entity.setId(getNextId());
            }
            map.put((ID) entity.getId(), entity);
        }
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

    private Long getNextId() {
        if (map.size() == 0) {
            return 0L;
        }
        else {
            return ((Long)Collections.max(map.keySet())) + 1L;
        }
    }
}
