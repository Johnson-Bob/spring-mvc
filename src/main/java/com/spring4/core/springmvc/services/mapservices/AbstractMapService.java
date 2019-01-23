package com.spring4.core.springmvc.services.mapservices;

import com.spring4.core.springmvc.domain.DomainObject;

import java.util.*;

public abstract class AbstractMapService<T extends DomainObject> {
    protected Map<Integer, T> domainMap;

    public AbstractMapService() {
        this.domainMap = new HashMap<>();
    }

    public List<T> listAll() {
        return new ArrayList<>(domainMap.values());
    }

    public T getById(Integer id) {
        return domainMap.get(id);
    }

    public T saveOrUpdate(T domainObject) {
        if (domainObject != null){

            if (domainObject.getId() == null){
                domainObject.setId(getNextKey());
            }
            domainMap.put(domainObject.getId(), domainObject);

            return domainObject;
        } else {
            throw new RuntimeException("Object Can't be null");
        }
    }

    public void delete(Integer id) {
        domainMap.remove(id);
    }

    private Integer getNextKey() {
        try {
            return Collections.max(domainMap.keySet()) + 1;
        } catch (NoSuchElementException e) {
            return 1;
        }

    }
}
