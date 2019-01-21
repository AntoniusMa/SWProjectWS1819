package de.ShopJohnson.sw.service.alternatives.Payment;

import de.ShopJohnson.sw.config.JohnsonConfig;
import de.ShopJohnson.sw.service.alternatives.AbstractPayService;
import de.ShopJohnson.sw.config.consts.TransactionStatus;
import de.ShopJohnson.sw.entity.ShopOrder;


import de.jevenari.sw.service.BankingException_Exception;
import de.jevenari.sw.service.PaymentService;
import de.jevenari.sw.service.PaymentServiceService;
import de.jevenari.sw.service.TransactionDTO;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.io.Serializable;

/**
 * Real implementation for the partner service from PayJohnson (Jonathan Evenari)
 */
@ApplicationScoped
@Alternative
public class PayService implements AbstractPayService, Serializable {

    @Inject
    Logger logger;

    private PaymentServiceService paymentServiceService = new PaymentServiceService();
    private PaymentService paymentService = paymentServiceService.getPaymentServicePort();

    /**
     * Instruct PayJohnson partner system to create and confirm a transaction
     * @param shopOrder The order
     * @param discountFactor discount factor of the Order
     * @param payJohnsonUsername Pay Johnson ID of the Customer
     * @param payJohnsonPassword Pay Johnson password of the Customer
     * @return Status string that indicates the outcome of the PayJohnson instruction
     */
    @Override
    public String instructJohnsonPayment(ShopOrder shopOrder , float discountFactor, String payJohnsonUsername, String payJohnsonPassword) {
        logger.info("Instructing Johnson payment");

        String ts;

        TransactionDTO transactionDTO = new TransactionDTO();

        // Fill transactionDTO with data

        transactionDTO.setAmount(shopOrder.getBillingAmount() * discountFactor);
        transactionDTO.setSourceUserName(payJohnsonUsername);
        transactionDTO.setSourceUserPassword(payJohnsonPassword);
        transactionDTO.setTargetUserName(JohnsonConfig.SHOP_PAYJOHNSON_ID);

        try {
            transactionDTO = paymentService.instructPayment(transactionDTO);
            shopOrder.getShopTransaction().setTransactionId(transactionDTO.getTransactionId());
            ts = TransactionStatus.TRANSACTION_DATA_CONFIRMED;
        }

        catch (BankingException_Exception be) {
            logger.warn(be.getFaultInfo().getMessage());
            ts = TransactionStatus.TRANSACTION_NOT_CONFIRMED;
        }

        catch (Exception e) {
            ts = TransactionStatus.COULD_NOT_REACH_PAY_JOHNSON;
            logger.warn("Couldn't get the PayJohnson");
        }
        return ts;
    }
}
