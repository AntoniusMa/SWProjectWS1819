package de.ShopJohnson.sw.entity.repo;

import de.ShopJohnson.sw.entity.DiscountCodeEntity;
import de.ShopJohnson.sw.entity.ShopOrder;
import de.ShopJohnson.sw.entity.util.SingleIdEntityRepository;
import de.ShopJohnson.sw.service.ShopOrderService;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;


/**
 * Repository for DiscountCodeEntities
 */
@Dependent
public class DiscountCodeRepo extends SingleIdEntityRepository<String, DiscountCodeEntity> {

    @Inject
    ShopOrderService shopOrderService;

    @Inject
    Logger logger;

    /**
     * Used to get all DiscountCodeEntities that are listed as invalid
     * @return List of invalid DiscountCodeeEntities
     */
    public List<DiscountCodeEntity> getInvalidDiscountCodes() {
        TypedQuery<DiscountCodeEntity> query = super.em.createQuery("select d from DiscountCodeEntity as d where" +
                " isValid = false ", DiscountCodeEntity.class);

        return query.getResultList();
    }

    /**
     * Removes all invalid DiscountCodeEntities from the database
     * @return List of removed DiscountCodeEntities
     */
    public List<DiscountCodeEntity> removeInvalid() {
        List<DiscountCodeEntity> invalid = getInvalidDiscountCodes();
        for (DiscountCodeEntity d : invalid) {
            try {
                ShopOrder so = shopOrderService.getShopOrderWithDiscountCode(d);
                so.setDiscountCode(null);
            }
            catch (NoResultException e) {
                logger.info("ShopOrder for DiscountCode " + d.getId() + " does not exist");
            }
            remove(d);
        }
        return invalid;
    }
}
