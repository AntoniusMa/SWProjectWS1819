package de.ShopJohnson.sw.ui.model;

import de.ShopJohnson.sw.service.CustomerService;
import de.ShopJohnson.sw.service.ShopOrderService;
import de.ShopJohsnon.sw.entity.Customer;
import de.ShopJohsnon.sw.entity.ShopOrder;
import org.primefaces.PrimeFaces;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class UserModel implements Serializable {

    @Inject private CustomerService customerService;

    @Inject
    ShopOrderService shopOrderService;

    private boolean loggedIn = false;
    private Customer customer = new Customer();
    private String loginStatus = "";
    private List<ShopOrder> shopOrders;

    public String login() {

        return "userPage.xhtml";
    }
    public String forwardToUserPageOrLogin() {
        PrimeFaces pf = PrimeFaces.current();
        if (loggedIn) {
            return "user";
        }
        else {
            return "login";
        }

    }
    public String tryLogin() {

        try {
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
        }
        catch (Exception e) {
            loginStatus = "Username or password wrong";
            return null;
        }

        return null;
    }

    public List<ShopOrder> getShopOrders() {
        return shopOrders;
    }

    public void setShopOrders(List<ShopOrder> shopOrders) {
        this.shopOrders = shopOrders;
    }

    public void getOrders() {
        customer = customerService.merge(customer);
        shopOrders = customer.getShopOrders();
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
