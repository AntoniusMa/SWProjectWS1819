package de.ShopJohnson.sw.service;

import de.ShopJohnson.sw.DTO.DiscountCode;
import de.ShopJohsnon.sw.entity.DiscountCodeEntity;
import de.ShopJohsnon.sw.entity.repo.DiscountCodeRepo;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@WebService
@RequestScoped
public class DiscountCodeService implements DiscountCodeIF{

    @Inject
    DiscountCodeRepo discountCodeRepo;

    @Transactional
    @Override
    public DiscountCode createSingleDiscountCode() {
        DiscountCodeEntity dc = new DiscountCodeEntity(0.50f);
        discountCodeRepo.persist(dc);
        return new DiscountCode(dc);
    }

    //@WebMethod
    @Override
    @Transactional
    public List<DiscountCode> createMultipleDiscountCodes(int amount) {
        List<DiscountCode> codeList = new ArrayList<DiscountCode>();
        for(int i=0; i < amount; i++) {
            codeList.add(createSingleDiscountCode());
        }
        return codeList;
    }
    @WebMethod(exclude = true)
    @Transactional
    public DiscountCode removeDiscountCode (DiscountCode dc) {
        DiscountCodeEntity dce = discountCodeRepo.getById(dc.getDiscountCode());
        discountCodeRepo.remove(dce);
        return dc;
    }
    @WebMethod(exclude = true)
    @Transactional
    public DiscountCode invalidateCode(DiscountCode dc) {
        DiscountCodeEntity dce = discountCodeRepo.getById(dc.getDiscountCode());
        dce.setValid(false);
        discountCodeRepo.merge(dce);
        return dc;
    }
}