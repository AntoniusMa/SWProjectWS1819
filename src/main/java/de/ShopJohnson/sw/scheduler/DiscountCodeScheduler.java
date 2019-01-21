package de.ShopJohnson.sw.scheduler;


import de.ShopJohnson.sw.entity.DiscountCodeEntity;
import de.ShopJohnson.sw.service.DiscountCodeService;
import de.ShopJohnson.sw.service.ShopOrderService;
//import de.ShopJohnson.sw.test.queue.TestPayJohnsonQueue;
//import de.jevenari.sw.service.TransactionDTO;
import org.jboss.logging.Logger;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.List;

@Startup
@Singleton
public class DiscountCodeScheduler {
    @Inject
    DiscountCodeService discountCodeService;
    @Inject
    ShopOrderService shopOrderService;

    @Inject
    Logger logger;


// Uncomment if testing the Pay Johnson queue locally
//    @Inject
//    TestPayJohnsonQueue testPayJohnsonQueue;

    /**
     * Timer to delete invalid Discount Codes from the database
     * Should be executed for example once a year, to avoid storing invalid discount codes forever.
     * Is executed once a minute to demonstrate the functionality
     */
    @Schedule(second = "*/60",
                minute = "*/1",
                hour = "*",
                persistent = false)
    public void deleteInvalidDiscountCodes () {
        List<DiscountCodeEntity> codes = discountCodeService.removeInvalidDiscountCodes();
        logger.info("ShopJohnson: removed " + codes.size() + " invalid DiscountCodes");
    }
//  Uncomment if testing queue locally
//    /**
//     * Function used to test the Pay Johnson queue locally.
//     * Send a TransactionDTO with payStatus true to the queue every 5 seconds
//     */
//    @Schedule(second = "*/5",
//            minute = "*/1",
//            hour = "*",
//            persistent = false)
//    public void testStatusQueue() {
//        TransactionDTO transactionDTO = new TransactionDTO();
//        transactionDTO.setTransactionId("8b7df57e-6397-415e-bd31-bfec15d65e07");
//        transactionDTO.setStatus(true);
//        testPayJohnsonQueue.sendPayUpdate(transactionDTO);
//    }
}
