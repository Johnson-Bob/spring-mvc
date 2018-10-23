package com.spring4.core.springmvc.services;

import com.spring4.core.springmvc.domain.Order;
import com.spring4.core.springmvc.services.CRUDService;

import java.util.List;

public interface OrderService extends CRUDService<Order> {
    @Override
    List<Order> listAll();

    @Override
    Order getById(Integer id);

    @Override
    Order saveOrUpdate(Order domainObject);

    @Override
    void delete(Integer id);
}
