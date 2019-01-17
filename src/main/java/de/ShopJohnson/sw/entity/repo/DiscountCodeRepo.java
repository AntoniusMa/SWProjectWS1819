package de.ShopJohnson.sw.entity.repo;

import de.ShopJohnson.sw.entity.DiscountCodeEntity;
import de.ShopJohnson.sw.entity.util.SingleIdEntityRepository;

import javax.enterprise.context.Dependent;

@Dependent
public class DiscountCodeRepo extends SingleIdEntityRepository<String, DiscountCodeEntity> {

}
