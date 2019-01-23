package com.spring4.core.springmvc;

import com.spring4.core.springmvc.domain.Address;
import com.spring4.core.springmvc.domain.Customer;
import com.spring4.core.springmvc.domain.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UtilClass {

    public static List<Product> generateListOfProduct() {
        List<Product> products = new ArrayList<>();

        Product product1 = new Product();
        product1.setDescription("Product 1");
        product1.setPrice(new BigDecimal("12.99"));
        product1.setImageUrl("http://example.com/product1");
        products.add(product1);

        Product product2 = new Product();
        product2.setDescription("Product 2");
        product2.setPrice(new BigDecimal("14.99"));
        product2.setImageUrl("http://example.com/product2");
        products.add(product2);

        Product product3 = new Product();
        product3.setDescription("Product 3");
        product3.setPrice(new BigDecimal("34.99"));
        product3.setImageUrl("http://example.com/product3");
        products.add(product3);

        Product product4 = new Product();
        product4.setDescription("Product 4");
        product4.setPrice(new BigDecimal("44.99"));
        product4.setImageUrl("http://example.com/product4");
        products.add(product4);

        Product product5 = new Product();
        product5.setDescription("Product 5");
        product5.setPrice(new BigDecimal("25.99"));
        product5.setImageUrl("http://example.com/product5");
        products.add(product5);

        return products;
    }

    public static List<Customer> generateListOfCustomers() {
        List<Customer> customers = new ArrayList<>();

        Customer customer1 = new Customer();
        customer1.setFirstName("Micheal");
        customer1.setLastName("Weston");
        customer1.setBillingAddress(new Address());
        customer1.getBillingAddress().setAddressLine1("1 Main St");
        customer1.getBillingAddress().setCity("Miami");
        customer1.getBillingAddress().setState("Florida");
        customer1.getBillingAddress().setZipCode("33101");
        customer1.setEmail("micheal@burnnotice.com");
        customer1.setPhoneNumber("305.333.0101");

        Customer customer2 = new Customer();
        customer2.setFirstName("Fiona");
        customer2.setLastName("Glenanne");
        customer2.setBillingAddress(new Address());
        customer2.getBillingAddress().setAddressLine1("1 Key Biscane Ave");
        customer2.getBillingAddress().setCity("Miami");
        customer2.getBillingAddress().setState("Florida");
        customer2.getBillingAddress().setZipCode("33101");
        customer2.setEmail("fiona@burnnotice.com");
        customer2.setPhoneNumber("305.323.0233");

        Customer customer3 = new Customer();
        customer3.setFirstName("Sam");
        customer3.setLastName("Axe");
        customer3.setBillingAddress(new Address());
        customer3.getBillingAddress().setAddressLine1("1 Little Cuba Road");
        customer3.getBillingAddress().setCity("Miami");
        customer3.getBillingAddress().setState("Florida");
        customer3.getBillingAddress().setZipCode("33101");
        customer3.setEmail("sam@burnnotice.com");
        customer3.setPhoneNumber("305.426.9832");

        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);

        return customers;
    }
}
