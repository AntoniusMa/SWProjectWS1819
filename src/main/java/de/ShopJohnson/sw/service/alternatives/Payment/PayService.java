package de.ShopJohnson.sw.service.alternatives.Payment;

import de.ShopJohnson.sw.JohnonConfig;
import de.ShopJohnson.sw.service.alternatives.AbstractPayService;
import de.ShopJohnson.sw.ui.consts.TransactionStatus;
import de.ShopJohsnon.sw.entity.ShopOrder;
import de.jevenari.sw.service.*;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.io.Serializable;

@ApplicationScoped
@Alternative
public class PayService implements AbstractPayService, Serializable {

    @Inject
    Logger logger;

    private PaymentServiceService paymentServiceService = new PaymentServiceService();
    private PaymentService paymentService = paymentServiceService.getPaymentServicePort();
    @Override
    public String instructJohnsonPayment(ShopOrder so) {
        logger.info("Instructing Johnson payment");
        String ts = TransactionStatus.TRANSACTION_NOT_CONFIRMED;
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAmount(so.getBillingAmount());
        transactionDTO.setSourceUserName(so.getShopTransaction().getSourceName());
        transactionDTO.setSourceUserPassword("test");
        transactionDTO.setTargetUserName(JohnonConfig.SHOP_PAYJOHNSON_ID);
        try {
            transactionDTO = paymentService.instructPayment(transactionDTO);
            ts = TransactionStatus.TRANSACTION_DATA_CONFIRMED;
        }
        catch (Exception e) {
            logger.warn("Couldn't get the PayJohnson");
        }
        return ts;
    }
}
