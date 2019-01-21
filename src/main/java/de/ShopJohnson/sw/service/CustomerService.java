package de.ShopJohnson.sw.service;


import de.ShopJohnson.sw.entity.Customer;
import de.ShopJohnson.sw.entity.repo.CustomerRepo;
import de.ShopJohnson.sw.entity.util.EntityUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import org.jboss.logging.Logger;


@RequestScoped
public class CustomerService {

    @Inject
    CustomerRepo customerRepo;

    @Inject
    Logger logger;
    /**
     * Hash password and register a new Customer
     * @param customer Customer to register
     * @return registered Customer or null (if something goes wrong)
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Customer register(Customer customer) {
        customer.setSalt(EntityUtils.createRandomString(32));
        try {
            customer.setPassword(EntityUtils.hashPassword(customer.getPassword(), customer.getSalt(), "SHA-256"));
        }
        catch (Exception e) {
            return null;
        }
        customer = persist(customer);

        return customer;
    }

    /**
     * Unregister an already registered Customer
     * @param customer Customer to unregister
     * @return unregistered Customer
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Customer unregister(Customer customer) {
        customer = customerRepo.getById(customer.getId());
        customerRepo.remove(customer);

        return customer;
    }

    /**
     * Makes persist available without specifically injecting the repository
     * @param customer Customer to persist
     * @return persisted Customer
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Customer persist(Customer customer) {
        customerRepo.persist(customer);
        return customer;
    }

    /**
     * Makes merge available without specifically injecting the repository
     * @param customer Customer to persist
     * @return merged Customer
     */
    public Customer merge(Customer customer) {
        return customerRepo.merge(customer);
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
     * Gets Customer by id
     * @param customerId id of the Customer
     * @return found Customer
     */
    public Customer getCustomerById(long customerId) {
        return customerRepo.getById(customerId);
    }

    /**
     * Changes data of the Customer
     * @param customer Customer Object with changed data
     * @return changed Customer
     */
    public Customer changeCustomerData(Customer customer) {
        return customerRepo.merge(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepo.getAll();
    }
}
