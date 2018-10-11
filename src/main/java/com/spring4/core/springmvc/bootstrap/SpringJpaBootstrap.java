package com.spring4.core.springmvc.bootstrap;

import com.spring4.core.springmvc.UtilClass;
import com.spring4.core.springmvc.services.CustomerService;
import com.spring4.core.springmvc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        UtilClass.generateListOfProduct().forEach(product -> productService.saveOrUpdate(product));
        UtilClass.generateListOfCustomers().forEach(customer -> customerService.saveOrUpdate(customer));
    }

}
