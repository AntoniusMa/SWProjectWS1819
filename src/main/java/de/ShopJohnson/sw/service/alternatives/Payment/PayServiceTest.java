package de.ShopJohnson.sw.service.alternatives.Payment;

import de.ShopJohnson.sw.service.alternatives.AbstractPayService;
import de.ShopJohnson.sw.config.consts.TransactionStatus;
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

    /**
     * Sleeps for one second to simulate web service call and return TransactionStatus(set manually on return statement)
     * @param shopOrder shopOrder that needs to be payed
     * @param discountFactor discountFactor of the used discount code
     * @param payJohnsonUsername PayJohnson username of the Customer
     * @param payJohnsonPassword PayJohnson password of the Customer
     * @return Transaction status string
     */
    @Override
    public String instructJohnsonPayment(ShopOrder shopOrder, float discountFactor, String payJohnsonUsername, String payJohnsonPassword) {
        try {
            Thread.sleep(1000);
        }
        catch (Exception e) {

        }
        return TransactionStatus.TRANSACTION_DATA_CONFIRMED;
    }
}
