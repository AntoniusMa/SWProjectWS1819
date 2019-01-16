package de.ShopJohnson.sw.service.alternatives;

import de.ShopJohnson.sw.Emeddables.ShopTransaction;
import de.ShopJohnson.sw.JohnonConfig;
import de.ShopJohnson.sw.service.alternatives.AbstractPayService;
import de.ShopJohnson.sw.ui.JohnsonUtil;
import de.ShopJohnson.sw.ui.consts.TransactionStatus;
import de.jevenari.sw.service.PaymentService;
import de.jevenari.sw.service.PaymentServiceService;
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

    PaymentServiceService paymentServiceService = new PaymentServiceService();
    PaymentService paymentService = paymentServiceService.getPaymentServicePort();
    @Override
    public String instructJohnsonPayment(ShopTransaction so) {
        logger.info("Instructing Johnson payment");

        return TransactionStatus.TRANSACTION_DATA_CONFIRMED;
    }
}
