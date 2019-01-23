package com.spring4.core.springmvc.services;

import com.spring4.core.springmvc.domain.User;

import java.util.List;

public interface UserService extends CRUDService<User> {

    @Override
    List<User> listAll();

    @Override
    User getById(Integer id);

    @Override
    User saveOrUpdate(User domainObject);

    @Override
    void delete(Integer id);
}
