package com.spring4.core.springmvc.services;

import com.spring4.core.springmvc.UtilClass;
import com.spring4.core.springmvc.domain.Product;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Profile("map")
public class ProductServiceImpl extends AbstractMapService<Product> implements ProductService {

}
