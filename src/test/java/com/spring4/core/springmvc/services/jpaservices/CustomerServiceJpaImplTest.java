package com.spring4.core.springmvc.services.jpaservices;

import com.spring4.core.springmvc.TestUtils;
import com.spring4.core.springmvc.configuration.JpaIntegrationConfig;
import com.spring4.core.springmvc.domain.Customer;
import com.spring4.core.springmvc.services.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaIntegrationConfig.class)
@DataJpaTest
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
        Integer id = 2;
        assertEquals(id, customerService.getById(id).getId());
    }

    @Test
    public void saveOrUpdate() {
        Integer id = 2;
        String testFirstName = "TestName";

//        Test update
        Customer customer = customerService.getById(id);
        customer.setFirstName(testFirstName);
        Customer savedCustomer = customerService.saveOrUpdate(customer);

        assertEquals(3, customerService.listAll().size());
        assertEquals(id, savedCustomer.getId());
        assertEquals(testFirstName, savedCustomer.getFirstName());

//        Test save
        savedCustomer = customerService.saveOrUpdate(TestUtils.createCustomer());
        assertEquals(4, customerService.listAll().size());

        customerService.delete(savedCustomer.getId());
    }

    @Test
    public void delete() {
        customerService.delete(3);

        assertEquals(2, customerService.listAll().size());

        customerService.saveOrUpdate(TestUtils.createCustomer());
    }

    @Test
    public void testSaveWithUser() {
        Customer newCustomer = TestUtils.createCustomer();
        newCustomer.setUser(TestUtils.createUser());

        Customer savedCustomer = customerService.saveOrUpdate(newCustomer);

        assertNotNull(savedCustomer.getId());
        assertNotNull(savedCustomer.getUser());
        assertNotNull(savedCustomer.getUser().getId());
        assertNotNull(savedCustomer.getUser().getEncryptedPassword());

        customerService.delete(savedCustomer.getId());
    }
}