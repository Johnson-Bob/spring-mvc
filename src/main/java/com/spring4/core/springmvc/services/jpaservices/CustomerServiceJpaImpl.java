package com.spring4.core.springmvc.services.jpaservices;

import com.spring4.core.springmvc.domain.Customer;
import com.spring4.core.springmvc.domain.User;
import com.spring4.core.springmvc.services.CustomerService;
import com.spring4.core.springmvc.services.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Service
@Profile("jpadao")
public class CustomerServiceJpaImpl implements CustomerService {

    @Autowired
    private EncryptionService encryptionService;

    private EntityManager entityManager;

    @PersistenceUnit
    public void setEntityManager(EntityManagerFactory entityManagerFactory) {
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

        User user = domainObject.getUser();
        if (user != null && user.getPassword() != null) {
            user.setEncryptedPassword(encryptionService.encryptionString(user.getPassword()));
        }
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
