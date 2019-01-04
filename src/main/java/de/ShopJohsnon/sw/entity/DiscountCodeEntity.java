package de.ShopJohsnon.sw.entity;

import de.ShopJohnson.sw.Dto.DiscountCode;
import de.ShopJohsnon.sw.entity.util.RandomIdEntity;

import javax.persistence.Entity;

@Entity
public class DiscountCodeEntity extends RandomIdEntity {

    private boolean isValid = true;
    private float amount;

    public DiscountCodeEntity() {

    }
    public DiscountCodeEntity(float amount) {
        this.amount = amount;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
