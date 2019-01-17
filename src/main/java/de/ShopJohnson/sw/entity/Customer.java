package de.ShopJohnson.sw.entity;

import de.ShopJohnson.sw.Emeddables.Address;
import de.ShopJohnson.sw.entity.util.GeneratedIdEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer extends GeneratedIdEntity {

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String salt;

    private Address address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ShopOrder> shopOrders;

    public Customer() {}

    public Customer(String firstname, String lastname, String username, String password, Address address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.address = address;
        this.password = password;
    }

    public long getCustomerId() {
        return super.getId();
    }

    public void setCustomerId(long customerId) {
        super.id = customerId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<ShopOrder> getShopOrders() {
        return shopOrders;
    }

    public void setShopOrders(List<ShopOrder> shopOrders) {
        this.shopOrders = shopOrders;
    }

    public boolean addShopOrder(ShopOrder shopOrder) {
        return shopOrders.add(shopOrder);
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "CustomerId = " + getCustomerId() +
                ", Firstname = " + getFirstname() +
                ", Lastname = " + getLastname() +
                ", Username " + getUsername() +
                ", Address " + getAddress() + "}";
    }
}
