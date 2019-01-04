package de.ShopJohnson.sw.ui.model;

import de.ShopJohnson.sw.service.CustomerService;
import de.ShopJohsnon.sw.entity.Customer;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class TestModel implements Serializable {

    @Inject private CustomerService customerService;

    private String username;
    private String password;

    private Customer customer = new Customer();

    public String login() {

        System.out.println("Starting login of " + username);

        return "test.xhtml";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
