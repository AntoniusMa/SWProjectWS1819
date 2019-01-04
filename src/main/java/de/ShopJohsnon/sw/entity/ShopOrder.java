package de.ShopJohsnon.sw.entity;

import de.ShopJohnson.sw.Emeddables.ShopTransaction;
import de.ShopJohsnon.sw.entity.util.GeneratedIdEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class ShopOrder extends GeneratedIdEntity {

    private float billingAmount;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Customer customer;
    @ManyToMany
    private List<Article> articles;
//    @Embedded
//    private ShopTransaction transaction;


    @OneToOne
    private DiscountCodeEntity discountCode;

    public ShopOrder() {

    }
    public ShopOrder(Customer customer, List<Article> articles) {
        this.customer = customer;
        this.articles = articles;
        this.billingAmount = 0;
        for (Article article : this.articles) {
            this.billingAmount += article.getPrice();
        }
    }


    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }


    public long getOrderId() {
        return super.id;
    }

    public void setOrderId(long id) {
        super.id = id;
    }

    public float getBillingAmount() {
        return billingAmount;
    }

    public void setBillingAmount(float billingAmount) {
        this.billingAmount = billingAmount;
    }

    public List<Article> getArticles() {
        return articles;
    }

//    public ShopTransaction getTransaction() {
//        return transaction;
//    }
//
//    public void setTransaction(ShopTransaction transaction) {
//        this.transaction = transaction;
//    }

    public DiscountCodeEntity getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(DiscountCodeEntity discountCode) {
        this.discountCode = discountCode;
    }

    @Override public String toString() {

    String s = "ShopOrder {" +
            "ID = " + super.id +
            ", Billing amount = " + billingAmount +
            ", Articles = { ";
    StringBuilder sb = new StringBuilder();
    sb.append(s);
    for (Article a : this.articles) {
        sb.append('\n');
    }
    s = sb.toString();
    return s;
    }


}
