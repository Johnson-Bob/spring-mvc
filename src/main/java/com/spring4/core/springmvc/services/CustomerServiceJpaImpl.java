package com.spring4.core.springmvc.services;

import com.spring4.core.springmvc.domain.Customer;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Service
@Profile("jpadao")
public class CustomerServiceJpaImpl implements CustomerService {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    @PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public List<Customer> listAll() {
        return entityManager.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
    }

    @Override
    public Customer getById(Integer id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    public Customer saveOrUpdate(Customer domainObject) {
        entityManager.getTransaction().begin();
        Customer savedCustomer = entityManager.merge(domainObject);
        entityManager.getTransaction().commit();

        return savedCustomer;
    }

    @Override
    public void delete(Integer id) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Customer.class, id));
        entityManager.getTransaction().commit();
    }
}
