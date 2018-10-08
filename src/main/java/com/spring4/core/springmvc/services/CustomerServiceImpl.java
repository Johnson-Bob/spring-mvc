package com.spring4.core.springmvc.services;

import com.spring4.core.springmvc.domain.Customer;

import java.util.*;

public class CustomerServiceImpl implements CustomerService {
    private Map<Integer, Customer> customers;

    @Override
    public List<Customer> listAllCustomers() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public Customer getCustomerById(Integer id) {
        return customers.get(id);
    }

    @Override
    public Customer saveOrUpdateCustomer(Customer customer) {
        if (customer != null) {
            if (customer.getId() == null) {
                customer.setId(getNextId());
            }
            customers.put(customer.getId(), customer);
            return customer;
        } else {
            throw new RuntimeException("Customer can't be null");
        }
    }

    @Override
    public void delete(Integer id) {
        customers.remove(id);
    }

    private void loadCustomers() {
        customers = new HashMap<>();
    }

    private Integer getNextId() {
        return Collections.max(customers.keySet()) + 1;
    }
}
