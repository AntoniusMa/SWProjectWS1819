package de.ShopJohsnon.sw.entity.repo;

import de.ShopJohsnon.sw.entity.DiscountCodeEntity;
import de.ShopJohsnon.sw.entity.util.SingleIdEntityRepository;

import javax.enterprise.context.Dependent;

@Dependent
public class DiscountCodeRepo extends SingleIdEntityRepository<String, DiscountCodeEntity> {

}
