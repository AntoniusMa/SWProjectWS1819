package de.ShopJohnson.sw.ui.model;

import de.ShopJohnson.sw.service.CustomerService;
import de.ShopJohnson.sw.service.ShopOrderService;
import de.ShopJohnson.sw.entity.Customer;
import de.ShopJohnson.sw.entity.ShopOrder;
import de.ShopJohnson.sw.entity.util.EntityUtils;
import org.primefaces.PrimeFaces;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class UserModel implements Serializable {

    @Inject private CustomerService customerService;

    @Inject private LoginModel loginModel;


    @Inject
    ShopOrderService shopOrderService;

    private Customer customer = new Customer();

    private boolean loggedIn = false;

    private List<ShopOrder> shopOrders;

    /**
     * Tries to login redirects to userPage if successful
     * @return
     */
    public String tryLogin() {

        try {
            Customer c = customerService.getCustomerByName(loginModel.getUsername());

            if(c.getPassword().equals(EntityUtils.hashPassword(loginModel.getPassword(), c.getSalt(), "SHA-256"))){
                customer = c;
                setLoggedIn(true);
                loginModel.setLoginStatus("");
                setSession();

                return "userPage.xhtml?faces-redirect=true";
            }
            else {
                loginModel.setLoginStatus("Username or password wrong");
            }
        }
        catch (Exception e) {
            loginModel.setLoginStatus("Username or password wrong");
        }
        return null;
    }

    /**
     * Logs out by destroying the Session and redirecting to home screen
     */
    public void logout() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("homeScreen.xhtml");
        }
        catch (Exception e) {

        }

    }

    /**
     * Sets session attribute to determine if user is logged in or not in LoginFilter
     */
    private void setSession() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        session.setAttribute("user", this);
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
}
