package de.ShopJohnson.sw.service;


import de.ShopJohsnon.sw.entity.Article;
import de.ShopJohsnon.sw.entity.Customer;
import de.ShopJohsnon.sw.entity.repo.CustomerRepo;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;


@RequestScoped
public class CustomerService {
//    @PersistenceContext(unitName = "examplePU")
//    private  EntityManager entityManager;
    @Inject
    CustomerRepo customerRepo;

    @Transactional
    public Customer register(Customer c) {
        customerRepo.persist(c);
        return c;
    }

    @Transactional
    public Customer unregister(Customer c) {
        c = customerRepo.merge(c);
        customerRepo.remove(c);

        return c;
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
