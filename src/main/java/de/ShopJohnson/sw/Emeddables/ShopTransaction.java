package de.ShopJohnson.sw.Emeddables;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ShopTransaction {


    private String receiverName;
    private Long payJohnsonId = -1l;
    private String payStatus;

    public ShopTransaction() {

    }

    public ShopTransaction(String receiverName, long payJohnsonId, String payStatus) {
        this.receiverName = receiverName;
        this.payJohnsonId = payJohnsonId;
        this.payStatus = payStatus;
    }
    @Column(nullable = true)
    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
    @Column(nullable = true)
    public long getPayJohnsonId() {
        return payJohnsonId;
    }

    public void setPayJohnsonId(long payJohnsonId) {
        this.payJohnsonId = payJohnsonId;
    }
    @Column(nullable = true)
    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    @Override
    public String toString() {
        return "ShopTransaction {" +
                " Receiver = " + receiverName +
                " Pay Johnson Id = " + payJohnsonId +
                " Status = " + payStatus + "}";
    }

}
