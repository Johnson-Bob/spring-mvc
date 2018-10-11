package com.spring4.core.springmvc.services;

import com.spring4.core.springmvc.configuration.JpaIntegrationConfig;
import com.spring4.core.springmvc.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaIntegrationConfig.class)
@ActiveProfiles("jpadao")
public class ProductServiceJpaImplTest {

    @Autowired
    private ProductService productService;

    @Test
    public void testListAll() {
        List<Product> products = productService.listAll();
        assertEquals(5, products.size());
    }

    @Test
    public void testGetById() {
        Integer id = 2;
        Product product = productService.getById(id);
        assertEquals(id, product.getId());
    }

    @Test
    public void testSaveOrUpdate() {
        Integer id = 2;
        String testDescription = "Test Product 2";

        Product product = productService.getById(id);
        product.setDescription(testDescription);
        Product savedProduct = productService.saveOrUpdate(product);

        assertEquals(id, savedProduct.getId());
        assertEquals(testDescription, savedProduct.getDescription());

        productService.saveOrUpdate(createProduct());
        assertEquals(6, productService.listAll().size());

        productService.delete(6);
    }

    @Test
    public void testDelete() {
        productService.delete(5);

        assertEquals(4, productService.listAll().size());

        productService.saveOrUpdate(createProduct());
    }

    private Product createProduct() {
        Product product = new Product();
        product.setDescription("Test Product");
        product.setPrice(new BigDecimal("11.11"));
        product.setImageUrl("http://test.img.com");

        return product;
    }
}