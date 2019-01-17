package de.ShopJohnson.sw.service.alternatives;

import de.ShopJohsnon.sw.entity.ShopOrder;

/**
 * Interface for implementing alternatives of PayJohnson Service implementations
 */
public interface AbstractPayService {
    String instructJohnsonPayment(ShopOrder shopTransaction);
}

