package de.ShopJohnson.sw.service.alternatives;

import de.ShopJohsnon.sw.entity.ShopOrder;

public interface AbstractPayService {
    String instructJohnsonPayment(ShopOrder shopTransaction);
}

