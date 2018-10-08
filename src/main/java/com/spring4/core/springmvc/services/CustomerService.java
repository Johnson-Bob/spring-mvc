package com.spring4.core.springmvc.services;

import com.spring4.core.springmvc.domain.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> listAllCustomers();
    Customer getCustomerById(Integer id);
    Customer saveOrUpdateCustomer(Customer customer);
    void delete(Integer id);
}
