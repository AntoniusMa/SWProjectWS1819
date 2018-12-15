package de.ShopJohsnon.sw.entity;

import de.ShopJohsnon.sw.entity.util.GeneratedIdEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Article extends GeneratedIdEntity {

    private String artName;
    private float price;

    public Article() {}

    public Article(String artName, float price) {
        this.artName = artName;
        this.price = price;
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
}
