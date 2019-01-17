package de.ShopJohnson.sw.entity;

import de.ShopJohnson.sw.entity.util.GeneratedIdEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Article extends GeneratedIdEntity {

    @NotNull
    private String artName;

    @NotNull
    private float price;

    private String description;

    private String picturePath;

    public Article() {}

    public Article(String artName, float price) {
        this.artName = artName;
        this.price = price;
    }

    public Article(String artName, float price, String description) {
        this.artName = artName;
        this.price = price;
        this.description = description;
    }

    public Article(String artName, float price, String description, String picturePath) {
        this.artName = artName;
        this.price = price;
        this.description = description;
        this.picturePath = picturePath;
    }

    public long getArtNr() {
        return super.id;
    }

    public void setArtNr(long artNr) {
        super.id = artNr;
    }

    public String getArtName() {
        return artName;
    }

    public void setArtName(String artName) {
        this.artName = artName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Article{" +
                "artNr=" + super.id +
                ", name='" + artName + '\'' +
                ", price=" + price +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
}
