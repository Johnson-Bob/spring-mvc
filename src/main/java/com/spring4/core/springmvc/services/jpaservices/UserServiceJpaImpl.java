package com.spring4.core.springmvc.services.jpaservices;

import com.spring4.core.springmvc.domain.User;
import com.spring4.core.springmvc.services.UserService;
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
public class UserServiceJpaImpl implements UserService {
    private EntityManager entityManager;

    @Autowired
    private EncryptionService encryptionService;

    @PersistenceUnit
    public void setEntityManager(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public List<User> listAll() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getById(Integer id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User saveOrUpdate(User domainObject) {
        entityManager.getTransaction().begin();
        if (domainObject.getPassword() != null) {
            domainObject.setEncryptedPassword(encryptionService.encryptionString(domainObject.getPassword()));
        }
        User savedUser = entityManager.merge(domainObject);
        entityManager.getTransaction().commit();

        return savedUser;
    }

    @Override
    public void delete(Integer id) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(User.class, id));
        entityManager.getTransaction().rollback();
    }
}
