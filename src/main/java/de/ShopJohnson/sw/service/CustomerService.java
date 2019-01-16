package de.ShopJohnson.sw.service;


import de.ShopJohsnon.sw.entity.Article;
import de.ShopJohsnon.sw.entity.Customer;
import de.ShopJohsnon.sw.entity.repo.CustomerRepo;
import de.ShopJohsnon.sw.entity.util.EntityUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.swing.text.html.CSS;
import javax.transaction.Transactional;
import java.security.MessageDigest;
import java.util.List;


@RequestScoped
public class CustomerService {
//    @PersistenceContext(unitName = "examplePU")
//    private  EntityManager entityManager;
    @Inject
    CustomerRepo customerRepo;

    @Transactional(Transactional.TxType.REQUIRED)
    public Customer register(Customer c) {
        c.setSalt(EntityUtils.createRandomString(32));
        try {
            c.setPassword(EntityUtils.hashPassword(c.getPassword(), c.getSalt(), "SHA-256"));
        }
        catch (Exception e) {
            return null;
        }
        customerRepo.persist(c);
        return c;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Customer unregister(Customer c) {
        c = customerRepo.merge(c);
        customerRepo.remove(c);

        return c;
    }
    @Transactional(Transactional.TxType.REQUIRED)
    public Customer persist(Customer c) {
        customerRepo.persist(c);
        return c;
    }
    public Customer merge(Customer c) {
        return customerRepo.merge(c);
    }

    public Customer getCustomerByName(String name) {
        return customerRepo.getCustomerByUsername(name);
    }
    public Customer getCustomerWithLogin(String username, String password){
        if(username==null || password == null) {
            return null;
        }
        return customerRepo.getCustomerByLogin(username, password);
    }
    public Customer getCustomerById(long customerId) {
        return customerRepo.getById(customerId);
    }
    public Customer changeCustomerData(Customer c) {
        return customerRepo.merge(c);
    }

    public List<Customer> getAllCustomers() {
        return customerRepo.getAll();
    }
}
