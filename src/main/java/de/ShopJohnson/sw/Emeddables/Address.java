package de.ShopJohnson.sw.Emeddables;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    
    private String plz;
    private String city;
    private String street;
    private String country;

    public Address() {

    }

    public Address(String plz, String city, String street, String country) {
        this.plz = plz;
        this.city = city;
        this.street = street;
        this.country = country;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    @Override
    public String toString() {
        return "Address{" +
                " PLZ="  + this.getPlz() +
                " City=" + this.getCity() +
                " Street" + this.getStreet() +
                " Country" + this.getCountry() + "}";
    }
}
