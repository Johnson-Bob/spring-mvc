package com.spring4.core.springmvc.services;

import com.spring4.core.springmvc.configuration.JpaIntegrationConfig;
import com.spring4.core.springmvc.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaIntegrationConfig.class)
@ActiveProfiles("jpadao")
public class CustomerServiceJpaImplTest {

    @Autowired
    private CustomerService customerService;

    @Test
    public void listAll() {
        assertEquals(3, customerService.listAll().size());
    }

    @Test
    public void getById() {
        Integer id = 7;
        assertEquals(id, customerService.getById(id).getId());
    }

    @Test
    public void saveOrUpdate() {
        Integer id = 7;
        String testFirstName = "TestName";

//        Test update
        Customer customer = customerService.getById(id);
        customer.setFirstName(testFirstName);
        Customer savedCustomer = customerService.saveOrUpdate(customer);

        assertEquals(3, customerService.listAll().size());
        assertEquals(id, savedCustomer.getId());
        assertEquals(testFirstName, savedCustomer.getFirstName());

//        Test save
        savedCustomer = customerService.saveOrUpdate(createCustomer());
        assertEquals(4, customerService.listAll().size());

        customerService.delete(savedCustomer.getId());
    }

    @Test
    public void delete() {
        customerService.delete(8);

        assertEquals(2, customerService.listAll().size());

        customerService.saveOrUpdate(createCustomer());
    }

    private Customer createCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Micheal");
        customer.setLastName("Weston");
        customer.setAddressLine1("1 Main St");
        customer.setCity("Miami");
        customer.setState("Florida");
        customer.setZipCode("33101");
        customer.setEmail("micheal@burnnotice.com");
        customer.setPhoneNumber("305.333.0101");

        return customer;
    }
}