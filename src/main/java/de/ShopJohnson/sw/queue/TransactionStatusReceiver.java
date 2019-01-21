package de.ShopJohnson.sw.queue;

import de.ShopJohnson.sw.entity.ShopOrder;
import de.ShopJohnson.sw.service.ShopOrderService;
import de.ShopJohnson.sw.config.consts.TransactionStatus;
import de.jevenari.sw.service.TransactionDTO;
import org.jboss.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;


/**
 * Queue that will receive updates from PayJohnson about the current status of a transaction
 * This class has only been tested locally (see test.queue.TestPayJohnsonQueue) but should as well work on the
 * central Wildfly Application Server
 */
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/queue/PayJohnson_ShopJohnson_TransactionStatusQueue"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class TransactionStatusReceiver implements MessageListener {
    @Inject
    ShopOrderService shopOrderService;

    @Inject
    Logger logger;

    /**
     * Callback function for messages on Pay Johnson's TransactionStatusQueue
     * This function will receive a TransactionDTO from Pay Johnson, find the corresponding ShopOrder and update its
     * payment status.
     * @param message Message from Pay Johnson's TransactionStatusQueue containing a TransactionDTO
     */
    @Override
    public void onMessage(Message message) {
        try {
            if(message instanceof ObjectMessage) {
                Object obj = ((ObjectMessage) message).getObject();
                if (obj instanceof String) {
                    String transactionJson = (String) obj;
                    JsonReader jsonReader = Json.createReader(new StringReader(transactionJson));
                    JsonObject jsonObject = jsonReader.readObject();
                    TransactionDTO transactionDTO = new TransactionDTO();
                    transactionDTO.setStatus(jsonObject.getBoolean("success"));
                    transactionDTO.setTransactionId(jsonObject.getString("transactionId"));
                    if(transactionDTO.isStatus()) {
                        ShopOrder shopOrder = shopOrderService.getWithTransactionId(transactionDTO.getTransactionId());
                        shopOrder.getShopTransaction().setPayStatus(TransactionStatus.TRANSACTION_CONFIRMED);
                    }
                    else {
                        ShopOrder shopOrder = shopOrderService.getWithTransactionId((transactionDTO.getTransactionId()));
                        shopOrder.getShopTransaction().setPayStatus(TransactionStatus.TRANSACTION_FAILED);
                    }
                    logger.info("Received new transaction");
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("Error during queue message");
        }
    }

}
