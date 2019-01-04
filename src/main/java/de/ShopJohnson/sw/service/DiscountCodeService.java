package de.ShopJohnson.sw.service;

import de.ShopJohnson.sw.Dto.DiscountCode;
import de.ShopJohsnon.sw.entity.DiscountCodeEntity;
import de.ShopJohsnon.sw.entity.repo.DiscountCodeRepo;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class DiscountCodeService {

    @Inject
    DiscountCodeRepo discountCodeRepo;

    @Transactional
    public DiscountCode createSingleDiscountCode() {
        DiscountCodeEntity dc = new DiscountCodeEntity(0.50f);
        discountCodeRepo.persist(dc);
        return new DiscountCode(dc);
    }

    public List<DiscountCode> createMultipleDiscountCodes(int amount) {
        List<DiscountCode> codeList = new ArrayList<DiscountCode>();
        for(int i=0; i < amount; i++) {
            codeList.add(createSingleDiscountCode());
        }
        return codeList;
    }
    @Transactional
    public DiscountCode removeDiscountCode (DiscountCode dc) {
        DiscountCodeEntity dce = discountCodeRepo.getById(dc.getDiscountCode());
        discountCodeRepo.remove(dce);
        return dc;
    }

    @Transactional
    public DiscountCode invalidateCode(DiscountCode dc) {
        DiscountCodeEntity dce = discountCodeRepo.getById(dc.getDiscountCode());
        dce.setValid(false);
        discountCodeRepo.merge(dce);
        return dc;
    }
}
