package com.spring4.core.springmvc.services;

import com.spring4.core.springmvc.domain.Product;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Service
@Profile("jpadao")
public class ProductServiceJpaImpl implements ProductService {

    private EntityManagerFactory emf;
    private EntityManager em;

    @PersistenceUnit
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
        this.em = emf.createEntityManager();
    }

    @Override
    public List<Product> listAll() {
        return em.createQuery("select p from Product p", Product.class).getResultList();
    }

    @Override
    public Product getById(Integer id) {
        return em.find(Product.class, id);
    }

    @Override
    public Product saveOrUpdate(Product domainObject) {
        em.getTransaction().begin();
        Product saveProduct = em.merge(domainObject);
        em.getTransaction().commit();

        return saveProduct;
    }

    @Override
    public void delete(Integer id) {
        em.getTransaction().begin();
        em.remove(em.find(Product.class, id));
        em.getTransaction().commit();
    }
}
