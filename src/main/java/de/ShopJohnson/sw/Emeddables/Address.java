package de.ShopJohnson.sw.Emeddables;

import javax.persistence.Embeddable;



@Embeddable
public class Address {
    
    private String zipCode;
    private String city;
    private String street;
    private String country;

    public Address() {

    }

    public Address(String plz, String city, String street, String country) {
        this.zipCode = plz;
        this.city = city;
        this.street = street;
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String plz) {
        this.zipCode = plz;
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
                " Zip code="  + this.getZipCode() +
                " City=" + this.getCity() +
                " Street" + this.getStreet() +
                " Country" + this.getCountry() + "}";
    }
}
