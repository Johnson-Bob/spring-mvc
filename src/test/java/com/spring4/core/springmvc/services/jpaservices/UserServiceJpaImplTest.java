package com.spring4.core.springmvc.services.jpaservices;

import com.spring4.core.springmvc.TestUtils;
import com.spring4.core.springmvc.configuration.JpaIntegrationConfig;
import com.spring4.core.springmvc.domain.*;
import com.spring4.core.springmvc.services.ProductService;
import com.spring4.core.springmvc.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaIntegrationConfig.class)
@ActiveProfiles("jpadao")
public class UserServiceJpaImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

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

    @Test
    public void testAddCartToUser() {
        User user = TestUtils.createUser();
        user.setCart(new Cart());

        User savedUser = userService.saveOrUpdate(user);

        assertNotNull(savedUser.getId());
        assertNotNull(savedUser.getVersion());
        assertNotNull(savedUser.getCart());
        assertNotNull(savedUser.getCart().getId());
    }

    @Test
    public void testAddCartToUserWithCartDetails() {
        User user = TestUtils.createUser();
        user.setCart(new Cart());

        List<Product> storedProducts = productService.listAll();

        CartDetail cartItemOne = new CartDetail();
        cartItemOne.setProduct(storedProducts.get(0));
        user.getCart().addCartDetail(cartItemOne);

        CartDetail cartItemTwo = new CartDetail();
        cartItemTwo.setProduct(storedProducts.get(1));
        user.getCart().addCartDetail(cartItemTwo);

        User savedUser = userService.saveOrUpdate(user);

        assertNotNull(savedUser.getId());
        assertNotNull(savedUser.getCart());
        assertNotNull(savedUser.getCart().getId());
        assertEquals(2, savedUser.getCart().getCartDetails().size());
        assertNotNull(savedUser.getCart().getCartDetails().get(0));
        assertNotNull(savedUser.getCart().getCartDetails().get(1));
    }

    @Test
    public void testAddAndRemoveCartToUserWithCartDetail() {
        User user = TestUtils.createUser();
        user.setCart(new Cart());

        List<Product> storedProducts = productService.listAll();

        CartDetail cartItemOne = new CartDetail();
        cartItemOne.setProduct(storedProducts.get(0));
        user.getCart().addCartDetail(cartItemOne);

        CartDetail cartItemTwo = new CartDetail();
        cartItemTwo.setProduct(storedProducts.get(1));
        user.getCart().addCartDetail(cartItemTwo);

        User savedUser = userService.saveOrUpdate(user);

        assertEquals(2, savedUser.getCart().getCartDetails().size());

        savedUser.getCart().removeCartDetail(savedUser.getCart().getCartDetails().get(0));
        userService.saveOrUpdate(savedUser);

        assertEquals(1, savedUser.getCart().getCartDetails().size());
    }
}