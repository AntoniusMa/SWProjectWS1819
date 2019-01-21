package de.ShopJohnson.sw.entity.repo;

import de.ShopJohnson.sw.entity.Customer;
import de.ShopJohnson.sw.entity.util.SingleIdEntityRepository;

import javax.enterprise.context.Dependent;
import javax.persistence.TypedQuery;

/**
 * Repository for Customer Entities
 */
@Dependent
public class CustomerRepo extends SingleIdEntityRepository<Long, Customer> {

    /**
     * Queries Customer by using the username
     * @param username Username of the Customer
     * @return found Customer
     */
    public Customer getCustomerByUsername(String username) {
        TypedQuery<Customer> query = em.createQuery("select c from Customer as c where lower(c.username)= :username",
                Customer.class);
        query.setParameter("username", username.toLowerCase());
        return query.getSingleResult();
    }

}
