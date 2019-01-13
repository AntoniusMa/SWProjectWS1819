package de.ShopJohnson.sw.service;

import de.ShopJohnson.sw.Emeddables.ShopTransaction;
import de.ShopJohnson.sw.ui.consts.TransactionStatus;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PayService {
    public String getJohnsonConfirmation (ShopTransaction shopTransaction) {
        try {
            Thread.sleep(1000);
        }
        catch (Exception e) {

        }
        return TransactionStatus.TRANSACTION_DATA_CONFIRMED;
    }
}
