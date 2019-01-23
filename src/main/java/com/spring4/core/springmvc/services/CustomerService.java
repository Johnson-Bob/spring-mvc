package com.spring4.core.springmvc.services;

import com.spring4.core.springmvc.domain.Customer;

import java.util.List;

public interface CustomerService extends CRUDService<Customer> {

    @Override
    List<Customer> listAll();

    @Override
    Customer getById(Integer id);

    @Override
    Customer saveOrUpdate(Customer domainObject);

    @Override
    void delete(Integer id);
}
