package com.spring4.core.springmvc.services;

import com.spring4.core.springmvc.domain.Product;

import java.util.List;

public interface ProductService extends CRUDService<Product> {
    @Override
    List<Product> listAll();

    @Override
    Product getById(Integer id);

    @Override
    Product saveOrUpdate(Product domainObject);

    @Override
    void delete(Integer id);
}
