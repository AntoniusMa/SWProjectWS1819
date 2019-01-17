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

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Article> articles;

    @Embedded
    private ShopTransaction shopTransaction;


    @OneToOne
    private DiscountCodeEntity discountCode;

    public ShopOrder() {

    }

    public ShopOrder(List<Article> articles) {
        this.articles = articles;
        calculateBillingAmount();
    }

    public ShopOrder(Customer customer, List<Article> articles, ShopTransaction shopTransaction) {
        this.customer = customer;
        this.articles = articles;
        this.billingAmount = 0;
        calculateBillingAmount();
        this.shopTransaction = shopTransaction;
    }

    /**
     * Calculates the billing amount of the ShopOrder by adding the amount of every Article in the Article List
     */
    private void calculateBillingAmount() {
        this.billingAmount = 0;
        for (Article a : this.articles) {
            this.billingAmount += a.getPrice();
        }
    }

    public void addArticle(Article a) {
        articles.add(a);
        this.billingAmount += a.getPrice();
    }

    public void removeArticle(Article a) {
        if(articles.isEmpty()) {
            return;
        }
        articles.remove(a);
        this.billingAmount -= a.getPrice();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public ShopTransaction getShopTransaction() {
        return shopTransaction;
    }

    public void setShopTransaction(ShopTransaction transaction) {
        this.shopTransaction = transaction;
    }

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
