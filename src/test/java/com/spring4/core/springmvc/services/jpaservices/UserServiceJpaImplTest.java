package com.spring4.core.springmvc.services.jpaservices;

import com.spring4.core.springmvc.TestUtils;
import com.spring4.core.springmvc.configuration.JpaIntegrationConfig;
import com.spring4.core.springmvc.domain.Customer;
import com.spring4.core.springmvc.domain.User;
import com.spring4.core.springmvc.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaIntegrationConfig.class)
@ActiveProfiles("jpadao")
public class UserServiceJpaImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void saveOrUpdate() {
        User user = TestUtils.createUser();
        User savedUser = userService.saveOrUpdate(user);

        assertNotNull(savedUser.getId());
        assertNotNull(savedUser.getEncryptedPassword());

        System.out.println("Encrypted password:");
        System.out.println(savedUser.getEncryptedPassword());
    }

    @Test
    public void saveUserWithCustomer() {
        User user = TestUtils.createUser();
        Customer customer = TestUtils.createCustomer();
        user.setCustomer(customer);

        User savedUser = userService.saveOrUpdate(user);

        assertNotNull(savedUser.getId());
        assertNotNull(savedUser.getVersion());
        assertNotNull(savedUser.getCustomer());
        assertNotNull(savedUser.getCustomer().getId());
    }
}