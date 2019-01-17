package de.ShopJohsnon.sw.entity.repo;

import de.ShopJohsnon.sw.entity.Customer;
import de.ShopJohsnon.sw.entity.util.SingleIdEntityRepository;

import javax.enterprise.context.Dependent;
import javax.persistence.TypedQuery;

@Dependent
public class CustomerRepo extends SingleIdEntityRepository<Long, Customer> {

    public Customer getCustomerByUsername(String username) {
        TypedQuery<Customer> query = em.createQuery("select c from Customer as c where lower(c.username)= :username",
                Customer.class);
        query.setParameter("username", username.toLowerCase());
        return query.getSingleResult();
    }

    public Customer getCustomerByLogin(String username, String password) {
        TypedQuery<Customer> query = em.createQuery("select c from Customer as c where lower(c.username)= :username " +
                                                        "and c.password= :password", Customer.class);
        query.setParameter("username", username.toLowerCase());
        query.setParameter("password", password);
        return query.getSingleResult();
    }
}
