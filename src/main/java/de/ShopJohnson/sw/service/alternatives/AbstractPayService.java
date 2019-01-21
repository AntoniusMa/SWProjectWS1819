package de.ShopJohnson.sw.service.alternatives;

import de.ShopJohnson.sw.entity.ShopOrder;

/**
 * Interface for implementing alternatives of PayJohnson Service implementations
 */
public interface AbstractPayService {
    String instructJohnsonPayment(ShopOrder shopOrder,float discountFactor, String payJohnsonUsername, String payJohnsonPassword);
}

