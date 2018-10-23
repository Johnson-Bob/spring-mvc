package com.spring4.core.springmvc.services.jpaservices;

import com.spring4.core.springmvc.domain.Order;
import com.spring4.core.springmvc.services.OrderService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Service
@Profile("jpadao")
public class OrderServiceJpaImpl implements OrderService {
    private EntityManager entityManager;

    @PersistenceUnit
    public void setEntityManager(EntityManagerFactory factory) {
        this.entityManager = factory.createEntityManager();
    }

    @Override
    public List<Order> listAll() {
        return entityManager.createQuery("select o from order_table o", Order.class).getResultList();
    }

    @Override
    public Order getById(Integer id) {
        return entityManager.find(Order.class, id);
    }

    @Override
    public Order saveOrUpdate(Order domainObject) {
        entityManager.getTransaction().begin();
        Order savedOrder = entityManager.merge(domainObject);
        entityManager.getTransaction().commit();
        return savedOrder;
    }

    @Override
    public void delete(Integer id) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Order.class, id));
        entityManager.getTransaction().commit();
    }
}
