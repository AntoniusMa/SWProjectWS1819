package de.ShopJohnson.sw.ui.model;

import de.ShopJohnson.sw.service.CustomerService;
import de.ShopJohsnon.sw.entity.Customer;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class UserModel implements Serializable {

    @Inject private CustomerService customerService;
    private boolean loggedIn = false;
    private Customer customer = new Customer();
    private String loginStatus = "";

    public String login() {

        return "userPage.xhtml";
    }
    public String forwardToUserPageOrLogin() {
        if (loggedIn) {
            return "userPage.xhtml";
        }
        else {
            return "login.xhtml";
        }

    }
    public String tryLogin() {
        Customer c = customerService.getCustomerWithLogin(customer.getUsername(), customer.getPassword());
        if (c == null){
            loginStatus = "Username or password wrong";
        }
        else {
            customer = c;
            loggedIn = true;
            loginStatus = "";
            return "userPage.xhtml";
        }
        return "";
    }
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }
}
