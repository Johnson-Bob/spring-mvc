package com.spring4.core.springmvc.controllers;

import com.spring4.core.springmvc.domain.Address;
import com.spring4.core.springmvc.domain.Customer;
import com.spring4.core.springmvc.services.CustomerService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void listCustomers() throws Exception{
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        customers.add(new Customer());

        when(customerService.listAll()).thenReturn((List) customers);

        mockMvc.perform(get("/customer/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/list"))
                .andExpect(model().attribute("customers", hasSize(2)));
    }

    @Test
    public void getCustomer() throws Exception {
        Integer id = 1;

        when(customerService.getById(id)).thenReturn(new Customer());

        mockMvc.perform(get("/customer/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/show"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void edit() throws Exception {
        Integer id = 1;

        when(customerService.getById(id)).thenReturn(new Customer());

        mockMvc.perform(get("/customer/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customerForm"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void newCustomer() throws Exception {
        verifyZeroInteractions(customerService);

        mockMvc.perform(get("/customer/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customerForm"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void saveOrUpdateCustomer() throws Exception {
        Integer id = 1;
        Customer returnCustomer = new Customer();
        String firstName = "Micheal";
        String lastName = "Weston";
        String addressLine1 = "1 Main St";
        String addressLine2 = "Apt 301";
        String city = "Miami";
        String state = "Florida";
        String zipCode = "33101";
        String email = "micheal@burnnotice.com";
        String phoneNumber = "305.333.0101";

        Address billingAddress = new Address();
        billingAddress.setAddressLine1(addressLine1);
        billingAddress.setAddressLine2(addressLine2);
        billingAddress.setCity(city);
        billingAddress.setState(state);
        billingAddress.setZipCode(zipCode);

        returnCustomer.setId(id);
        returnCustomer.setFirstName(firstName);
        returnCustomer.setLastName(lastName);
        returnCustomer.setBillingAddress(billingAddress);
        returnCustomer.setEmail(email);
        returnCustomer.setPhoneNumber(phoneNumber);

        when(customerService.saveOrUpdate(Matchers.<Customer>any())).thenReturn(returnCustomer);

        mockMvc.perform(post("/customer")
                .param("id", "1")
                .param("firstName", firstName)
                .param("lastName", lastName)
                .param("shippingAddress.addressLine1", addressLine1)
                .param("shippingAddress.addressLine2", addressLine2)
                .param("shippingAddress.city", city)
                .param("shippingAddress.state", state)
                .param("shippingAddress.zipCode", zipCode)
                .param("email", email)
                .param("phoneNumber", phoneNumber))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customer/1"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)))
                .andExpect(model().attribute("customer", hasProperty("firstName", is(firstName))))
                .andExpect(model().attribute("customer", hasProperty("lastName", is(lastName))))
                .andExpect(model().attribute("customer", hasProperty("shippingAddress",
                        hasProperty("addressLine1", is(addressLine1)))))
                .andExpect(model().attribute("customer", hasProperty("shippingAddress",
                        hasProperty("addressLine2", is(addressLine2)))))
                .andExpect(model().attribute("customer", hasProperty("shippingAddress",
                        hasProperty("city", is(city)))))
                .andExpect(model().attribute("customer", hasProperty("shippingAddress",
                        hasProperty("state", is(state)))))
                .andExpect(model().attribute("customer", hasProperty("shippingAddress",
                        hasProperty("zipCode", is(zipCode)))))
                .andExpect(model().attribute("customer", hasProperty("email", is(email))))
                .andExpect(model().attribute("customer", hasProperty("phoneNumber", is(phoneNumber))));

        ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(customerService).saveOrUpdate(customerCaptor.capture());

        Customer boundCustomer = customerCaptor.getValue();

        assertEquals(id, boundCustomer.getId());
        assertEquals(firstName, boundCustomer.getFirstName());
        assertEquals(lastName, boundCustomer.getLastName());
        assertEquals(addressLine1, boundCustomer.getShippingAddress().getAddressLine1());
        assertEquals(addressLine2, boundCustomer.getShippingAddress().getAddressLine2());
        assertEquals(city, boundCustomer.getShippingAddress().getCity());
        assertEquals(state, boundCustomer.getShippingAddress().getState());
        assertEquals(zipCode, boundCustomer.getShippingAddress().getZipCode());
        assertEquals(email, boundCustomer.getEmail());
        assertEquals(phoneNumber, boundCustomer.getPhoneNumber());
    }

    @Test
    public void delete() throws Exception {
        Integer id = 1;

        mockMvc.perform(get("/customer/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customer/list"));

        verify(customerService, times(1)).delete(id);
    }
}