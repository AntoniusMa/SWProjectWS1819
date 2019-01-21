package de.ShopJohnson.sw.service;

import de.ShopJohnson.sw.DTO.DiscountCode;
import de.ShopJohnson.sw.config.JohnsonConfig;
import de.ShopJohnson.sw.entity.DiscountCodeEntity;
import de.ShopJohnson.sw.entity.repo.DiscountCodeRepo;
import de.ShopJohnson.sw.service.exception.DiscountCodeException;

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

    /**
     * creates a single DiscountCodeEntity with discount amount of JohnsonConfig.DISCOUNT_CODE_DEFAULT_AMOUNT
     * @return DiscountCode Object of generated DiscountCodeEntity
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    @WebMethod(exclude = true)
    public DiscountCode createSingleDiscountCode() {
        DiscountCodeEntity dc = new DiscountCodeEntity(JohnsonConfig.DISCOUNT_CODE_DEFAULT_AMOUNT);
        discountCodeRepo.persist(dc);
        return new DiscountCode(dc);
    }

    /**
     * Webservice function that is used by partner systems to generate DiscountCodes for ShopJohnson
     * @param amount Amount of DiscountCodes
     * @return List of DiscountCode objects
     * @throws DiscountCodeException Exception thrown when something went wrong during execute of the service
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public List<DiscountCode> createMultipleDiscountCodes(int amount) throws DiscountCodeException {
        List<DiscountCode> codeList = new ArrayList<DiscountCode>();
        try {
            for (int i = 0; i < amount; i++) {
                codeList.add(createSingleDiscountCode());
            }
        } catch (Exception e) {
            throw new DiscountCodeException();
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

    /**
     * Removes invalid DiscountCodeEntities from the database
     * @return List of removed DiscountCodeEntities
     */
    @Transactional(Transactional.TxType.REQUIRED)
    @WebMethod(exclude = true)
    public List<DiscountCodeEntity> removeInvalidDiscountCodes() {
        return discountCodeRepo.removeInvalid();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    @WebMethod(exclude = true)
    public DiscountCodeEntity getById(String id) {
        return discountCodeRepo.getById(id);
    }
}