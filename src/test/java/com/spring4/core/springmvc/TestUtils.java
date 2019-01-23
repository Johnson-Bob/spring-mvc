package com.spring4.core.springmvc;

import com.spring4.core.springmvc.domain.*;

import java.math.BigDecimal;

public class TestUtils {
    public static Customer createCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Micheal");
        customer.setLastName("Weston");
        customer.setBillingAddress(new Address());
        customer.getBillingAddress().setAddressLine1("1 Main St");
        customer.getBillingAddress().setCity("Miami");
        customer.getBillingAddress().setState("Florida");
        customer.getBillingAddress().setZipCode("33101");
        customer.setEmail("micheal@burnnotice.com");
        customer.setPhoneNumber("305.333.0101");

        return customer;
    }

    public static Product createProduct() {
        Product product = new Product();
        product.setDescription("Test Product");
        product.setPrice(new BigDecimal("11.11"));
        product.setImageUrl("http://test.img.com");

        return product;
    }

    public static User createUser() {
        User user = new User();
        user.setUsername("someusername");
        user.setPassword("myPassword");

        return user;
    }


}
