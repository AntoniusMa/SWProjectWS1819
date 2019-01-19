package de.ShopJohnson.sw.Queue;

import de.ShopJohnson.sw.service.ShopOrderService;
import de.jevenari.sw.service.TransactionDTO;
import org.jboss.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/queue/PayJohnson_ShopJohnson_TransactionStatusQueue"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class TransactionStatusReceiver implements MessageListener {
    @Inject
    ShopOrderService shopOrderService;

    @Inject
    Logger logger;

    @Override
    public void onMessage(Message message) {
        try {
            if(message instanceof ObjectMessage) {
                Object obj = ((ObjectMessage) message).getObject();
                if (obj instanceof TransactionDTO) {
                    TransactionDTO transactionDTO = (TransactionDTO) obj;
                    if(transactionDTO.isStatus()) {

                    }
                    logger.info("Received new transaction");
                }
            }
        }
        catch (Exception e) {
            logger.warn("Error during queue message");
        }
    }

}
