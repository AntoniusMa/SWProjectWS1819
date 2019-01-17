package de.ShopJohnson.sw.DTO;

import de.ShopJohsnon.sw.entity.DiscountCodeEntity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


/**
 * DTO for discount codes
 *
 * Could also send the DiscountCodeEntity, but users of the webservice don't need to see the whole entity and shouldn't
 * see all attributes of the entity
 *
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DiscountCode implements Serializable {

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