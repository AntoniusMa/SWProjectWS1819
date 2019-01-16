package de.ShopJohnson.sw.service.alternatives;

import de.ShopJohnson.sw.Emeddables.ShopTransaction;

public interface AbstractPayService {
    String instructJohnsonPayment(ShopTransaction shopTransaction);
}

