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

    /**
     * Hash password and register a new Customer
     * @param c Customer to register
     * @return registered Customer or null (if something goes wrong)
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Customer register(Customer c) {
        c.setSalt(EntityUtils.createRandomString(32));
        try {
            c.setPassword(EntityUtils.hashPassword(c.getPassword(), c.getSalt(), "SHA-256"));
        }
        catch (Exception e) {
            return null;
        }
        c = persist(c);
        return c;
    }

    /**
     * Unregister an already registered Customer
     * @param c Customer to unregister
     * @return unregistered Customer
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Customer unregister(Customer c) {
        c = customerRepo.getById(c.getId());
        customerRepo.remove(c);

        return c;
    }

    /**
     * Makes persist available without specifically injecting the repository
     * @param c Customer to persist
     * @return persisted Customer
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Customer persist(Customer c) {
        customerRepo.persist(c);
        return c;
    }

    /**
     * Makes merge available without specifically injecting the repository
     * @param c Customer to persist
     * @return merged Customer
     */
    public Customer merge(Customer c) {
        return customerRepo.merge(c);
    }

    /**
     * Gets Customer by username
     * @param username of the Customer
     * @return found Customer
     */
    public Customer getCustomerByName(String username) {
        return customerRepo.getCustomerByUsername(username);
    }

    /**
     * Gets Customer with username and hashed password
     * @param username username of the Customer
     * @param password hashed password of the Customer
     * @return found Customer
     */
    public Customer getCustomerWithLogin(String username, String password){
        if(username==null || password == null) {
            return null;
        }
        return customerRepo.getCustomerByLogin(username, password);
    }

    /**
     * Gets Customer by id
     * @param customerId id of the Customer
     * @return found Customer
     */
    public Customer getCustomerById(long customerId) {
        return customerRepo.getById(customerId);
    }

    /**
     * Changes data of the Customer
     * @param c Customer Object with changed data
     * @return changed Customer
     */
    public Customer changeCustomerData(Customer c) {
        return customerRepo.merge(c);
    }

    public List<Customer> getAllCustomers() {
        return customerRepo.getAll();
    }
}
