// Uncomment if you want to test the PayJohnsonQueue locally

//package de.ShopJohnson.sw.test.queue;
//
//
//import de.TransactionDTO;
//import org.jboss.logging.Logger;
//
//import javax.annotation.Resource;
//import javax.enterprise.context.RequestScoped;
//import javax.inject.Inject;
//import javax.jms.*;
//
//@RequestScoped
//public class TestPayJohnsonQueue {
//    @Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
//    private static ConnectionFactory connectionFactory;
//
//    @Resource(lookup = "java:/jms/queue/PayJohnson_ShopJohnson_TransactionStatusQueue")
//    private Queue queue;
//
//    @Inject
//    Logger logger;
//
//    public void sendPayUpdate(TransactionDTO transactionDTO) {
//        try (JMSContext context = connectionFactory.createContext()) {
//            JMSProducer producer = context.createProducer();
//            ObjectMessage objectMessage = context.createObjectMessage();
//            objectMessage.setObject(transactionDTO);
//            producer.send(queue, objectMessage);
//
//        }
//        catch (JMSException e) {
//            logger.warn("Problem while writing on queue");
//        }
//    }
//}
