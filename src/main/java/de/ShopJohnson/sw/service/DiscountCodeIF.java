package de.ShopJohnson.sw.service;

import de.ShopJohnson.sw.DTO.DiscountCode;

import java.util.List;

public interface DiscountCodeIF {
    public DiscountCode createSingleDiscountCode();
    public List<DiscountCode> createMultipleDiscountCodes(int amount);
}
