package de.ShopJohnson.sw.Dto;

import de.ShopJohsnon.sw.entity.DiscountCodeEntity;

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
