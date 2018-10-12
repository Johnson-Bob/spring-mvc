package com.spring4.core.springmvc.services.mapservices;

import com.spring4.core.springmvc.domain.Product;
import com.spring4.core.springmvc.services.ProductService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("map")
public class ProductServiceImpl extends AbstractMapService<Product> implements ProductService {

}
