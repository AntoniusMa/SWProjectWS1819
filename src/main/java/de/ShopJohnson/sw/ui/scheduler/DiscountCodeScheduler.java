package de.ShopJohnson.sw.ui.scheduler;


import de.ShopJohnson.sw.entity.DiscountCodeEntity;
import de.ShopJohnson.sw.service.DiscountCodeService;
import org.jboss.logging.Logger;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Startup
@Singleton
public class DiscountCodeScheduler {
    @Inject
    DiscountCodeService discountCodeService;

    @Schedule(second = "*/60",
            minute = "*/1",
            hour = "*",
            persistent = false)
    public void ping() {
        System.out.println("Hallo ich bein ein schedelar");
    }
    @Schedule(second = "*/60",
                minute = "*/1",
                hour = "*",
                persistent = false)
    public void deleteInvalidDiscountCodes () {
        List<DiscountCodeEntity> codes = discountCodeService.removeInvalidDiscountCodes();
        System.out.println("ShopJohnson: removed " + codes.size() + " invalid DiscountCodes");
    }
}
