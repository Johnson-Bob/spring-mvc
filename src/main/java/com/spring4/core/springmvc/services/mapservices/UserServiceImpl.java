package com.spring4.core.springmvc.services.mapservices;

import com.spring4.core.springmvc.domain.User;
import com.spring4.core.springmvc.services.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("map")
public class UserServiceImpl extends AbstractMapService<User> implements UserService {
}
