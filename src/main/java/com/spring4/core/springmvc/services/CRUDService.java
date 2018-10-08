package com.spring4.core.springmvc.services;

import com.spring4.core.springmvc.domain.DomainObject;

import java.util.List;

public interface CRUDService<T extends DomainObject> {
    List<T> listAll();
    T getById(Integer id);
    T saveOrUpdate(T domainObject);
    void delete(Integer id);
}
