package com.spring4.core.springmvc.services.mapservices;

import com.spring4.core.springmvc.domain.Order;
import com.spring4.core.springmvc.services.CRUDService;
import com.spring4.core.springmvc.services.OrderService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("map")
public class OrderServiceImpl extends AbstractMapService<Order> implements OrderService {
}
