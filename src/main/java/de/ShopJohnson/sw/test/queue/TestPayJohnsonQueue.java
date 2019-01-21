// Uncomment if you want to test the PayJohnsonQueue locally
//
//package de.ShopJohnson.sw.test.queue;
//
//
// import de.jevenari.sw.service.TransactionDTO;
// import org.jboss.logging.Logger;
//
//import javax.annotation.Resource;
//import javax.enterprise.context.RequestScoped;
//import javax.inject.Inject;
//import javax.jms.*;
// import javax.json.Json;
// import javax.json.JsonObject;
// import javax.json.JsonObjectBuilder;
// import java.io.IOException;
// import java.io.StringWriter;
// import java.io.Writer;
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
//            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder()
//                    .add("transactionId", transactionDTO.getTransactionId())
//                    .add("success", transactionDTO.isStatus());
//            JsonObject jsonObject = jsonObjectBuilder.build();
//            String jsonString = "";
//            try(Writer writer = new StringWriter()){
//                Json.createWriter(writer).write(jsonObject);
//                jsonString = writer.toString();
//            }
//            catch (IOException e) {
//                e.printStackTrace();
//            }
//            objectMessage.setObject(jsonString);
//            producer.send(queue, objectMessage);
//
//        }
//        catch (JMSException e) {
//            logger.warn("Problem while writing on queue");
//        }
//    }
//}
