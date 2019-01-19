package de.ShopJohnson.sw.service.alternatives.Payment;

import de.ShopJohnson.sw.service.alternatives.AbstractPayService;
import de.ShopJohnson.sw.ui.consts.TransactionStatus;
import de.ShopJohnson.sw.entity.ShopOrder;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.io.Serializable;


/**
 * Substitution for PayJohnson Webservice
 */
@ApplicationScoped
@Alternative
public class PayServiceTest implements AbstractPayService, Serializable {

    @Override
    public String instructJohnsonPayment(ShopOrder shopOrder,float discountFactor, String payJohnsonId, String payJohnsonPassword) {
        try {
            Thread.sleep(1000);
        }
        catch (Exception e) {

        }
        return TransactionStatus.TRANSACTION_NOT_CONFIRMED;
    }
}
