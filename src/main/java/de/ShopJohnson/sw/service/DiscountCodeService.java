package de.ShopJohnson.sw.service;

import de.ShopJohnson.sw.DTO.DiscountCode;
import de.ShopJohnson.sw.entity.DiscountCodeEntity;
import de.ShopJohnson.sw.entity.repo.DiscountCodeRepo;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebService
@ApplicationScoped
public class DiscountCodeService implements DiscountCodeIF, Serializable {

    @Inject
    DiscountCodeRepo discountCodeRepo;

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    @WebMethod(exclude = true)
    public DiscountCode createSingleDiscountCode() {
        DiscountCodeEntity dc = new DiscountCodeEntity(0.50f);
        discountCodeRepo.persist(dc);
        return new DiscountCode(dc);
    }

    //@WebMethod
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public List<DiscountCode> createMultipleDiscountCodes(int amount) {
        List<DiscountCode> codeList = new ArrayList<DiscountCode>();
        for(int i=0; i < amount; i++) {
            codeList.add(createSingleDiscountCode());
        }
        return codeList;
    }

    /**
     * Removes DiscountCode from Database
     * @param dc DiscountCode to remove
     * @return removed DiscountCode
     */
    @WebMethod(exclude = true)
    @Transactional(Transactional.TxType.REQUIRED)
    public DiscountCode removeDiscountCode (DiscountCode dc) {
        DiscountCodeEntity dce = discountCodeRepo.getById(dc.getDiscountCode());
        discountCodeRepo.remove(dce);
        return dc;
    }

    /**
     * Set validity of DiscountCode false
     * @param dc Discount code to invalidate
     * @return invalidated DiscountCode
     */
    @WebMethod(exclude = true)
    @Transactional(Transactional.TxType.REQUIRED)
    public DiscountCode invalidateCode(DiscountCode dc) {
        DiscountCodeEntity dce = discountCodeRepo.getById(dc.getDiscountCode());
        dce.setValid(false);
        discountCodeRepo.merge(dce);
        return dc;
    }

    /**
     * Function to generate a DiscountCode the Customer.
     * Randomly decides whether or not a Customer receives a DiscountCode at the end of his Order
     * @return new DiscountCode or null
     */
    @Transactional(Transactional.TxType.REQUIRED)
    @WebMethod(exclude = true)
    public DiscountCode generateBonusDiscountCode() {
        Random random = new Random();
        boolean decider = random.nextBoolean();
        if(decider) {
            return this.createSingleDiscountCode();
        }
        return null;
    }
}