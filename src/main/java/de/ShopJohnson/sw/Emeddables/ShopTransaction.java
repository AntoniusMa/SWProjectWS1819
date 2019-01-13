package de.ShopJohnson.sw.Emeddables;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ShopTransaction {


    private String sourceName;
    private String payStatus;

    public ShopTransaction() {

    }

    public ShopTransaction(String sourceName, String payStatus) {
        this.sourceName = sourceName;
        this.payStatus = payStatus;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String receiverName) {
        this.sourceName = receiverName;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    @Override
    public String toString() {
        return "ShopTransaction {" +
                " Receiver = " + sourceName +
                " Status = " + payStatus + "}";
    }

}
