package com.spring4.core.springmvc.services;

import com.spring4.core.springmvc.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> listAllProducts();
    Product getProductById(Integer id);
    Product saveOrUpdateProduct(Product product);
}
