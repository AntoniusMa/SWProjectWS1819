package de.ShopJohnson.sw.DTO;

import de.ShopJohsnon.sw.entity.DiscountCodeEntity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DiscountCode {

    private String discountCode;
    private float amount;

    public DiscountCode() {

    }
    public DiscountCode(DiscountCodeEntity discountCodeEntity) {
        this.discountCode = discountCodeEntity.getId();
        this.amount = discountCodeEntity.getAmount();
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}