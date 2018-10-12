package com.spring4.core.springmvc.services.mapservices;

import com.spring4.core.springmvc.domain.Customer;
import com.spring4.core.springmvc.services.CustomerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("map")
public class CustomerServiceImpl extends AbstractMapService<Customer> implements CustomerService {

}
