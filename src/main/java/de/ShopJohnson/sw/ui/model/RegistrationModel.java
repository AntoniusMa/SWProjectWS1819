package de.ShopJohnson.sw.ui.model;

import de.ShopJohnson.sw.Emeddables.Address;
import de.ShopJohnson.sw.service.CustomerService;
import de.ShopJohnson.sw.ui.consts.RegistrationStatus;
import de.ShopJohsnon.sw.entity.Customer;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.PrimeFaces;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;

@Named
@RequestScoped
public class RegistrationModel implements Serializable {
    @Inject private CustomerService customerService;
    private Customer customer = new Customer();
    private Address address = new Address();
    private String registrationStatus;
    private String registrationMessage;
    public void registerCustomer() {
        customer.setAddress(address);
        System.out.println("registering");
        if(checkEmptyInputs()) {
            return;
        }
        try {

            Customer c = customerService.register(customer);
            registrationStatus = RegistrationStatus.SUCCESSFUL.toString();
            registrationMessage = "Successfully registered new Customer";

            System.out.println(customer.getCustomerId());
        }
        catch (Exception e) {
            registrationStatus = RegistrationStatus.FAILED.toString();
            if(e.getCause().getCause() instanceof ConstraintViolationException) {
                registrationMessage = "Username already exists";
            }
            else {
                registrationMessage = "Couldn't register due to internal database error";
            }
        }

    }

    private boolean checkEmptyInputs() {
        if(customer.getUsername() == null) {
            registrationMessage = "Missing entry for username";
        }
        else if(customer.getLastname() == null) {
            registrationMessage = "Missing entry for last name";
        }
        else if(customer.getFirstname() == null) {
            registrationMessage = "Missing entry for first name";
        }
        else if(customer.getPassword() == null) {
            registrationMessage = "Missing entry for password";
        }
        else {
            return false;
        }
        registrationStatus = RegistrationStatus.FAILED.toString();
        return true;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(String registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    public String getRegistrationMessage() {
        return registrationMessage;
    }

    public void setRegistrationMessage(String registrationMessage) {
        this.registrationMessage = registrationMessage;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
