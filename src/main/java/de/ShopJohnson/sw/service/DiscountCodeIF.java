package de.ShopJohnson.sw.service;

import de.ShopJohnson.sw.DTO.DiscountCode;
import de.ShopJohnson.sw.service.exception.DiscountCodeException;

import java.util.List;


public interface DiscountCodeIF {
    /**
     * Creates a single DiscountCode and persists it
     * @return new DiscountCode
     */
    public DiscountCode createSingleDiscountCode();

    /**
     * Creates multiple DiscountCodes and persists them
     * @param amount Amount of DiscountCodes
     * @return List of DiscountCodes
     */
    public List<DiscountCode> createMultipleDiscountCodes(int amount) throws DiscountCodeException;
}