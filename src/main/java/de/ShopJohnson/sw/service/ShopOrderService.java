package de.ShopJohnson.sw.service;

import de.ShopJohnson.sw.config.JohnsonConfig;
import de.ShopJohnson.sw.entity.Customer;
import de.ShopJohnson.sw.entity.DiscountCodeEntity;
import de.ShopJohnson.sw.entity.ShopOrder;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class ShopOrderService {
    @PersistenceContext(unitName = JohnsonConfig.PERSISTANCE_UNIT_NAME)
    private EntityManager entityManager;

    @Inject
    CustomerService customerService;

    /**
     * Persists a ShopOrder and adds it to the orders list of its Customer
     * @param shopOrder ShopOrder to persist
     * @return persisted ShopOrder
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ShopOrder persistShopOrder(ShopOrder shopOrder) {
        shopOrder.setCustomer(customerService.getCustomerByName(shopOrder.getCustomer().getUsername()));
        shopOrder.getCustomer().addShopOrder(shopOrder);
        DiscountCodeEntity dc = null;
        if(shopOrder.getDiscountCode() != null) {
            dc = entityManager.merge(shopOrder.getDiscountCode());
        }
        shopOrder.setDiscountCode(dc);
        if(dc != null) {
            dc.setValid(false);
        }
        entityManager.persist(shopOrder);
        customerService.persist(shopOrder.getCustomer());
        return shopOrder;
    }

    /**
     * Gets all ShopOrders of a Customer
     * @param customer Customer who requests his ShopOrders
     * @return List of all ShopOrders customer
     */
    public List<ShopOrder> getCustomersOrders(Customer customer) {
        TypedQuery<ShopOrder> query = entityManager.createQuery(
                "select o from ShopOrder as o where o.customer.id = :customer", ShopOrder.class
        );
        query.setParameter("customer", customer.getId());
        return  query.getResultList();
    }
    public ShopOrder getById(long id) {
        return entityManager.find(ShopOrder.class, id);
    }

    /**
     * Finds a ShopOrder buy using DiscountCodeEntity that was used for it
     * @param discountCodeEntity DiscountCodeEntity that was used for a ShopOrder
     * @return ShopOrder that used the discountCodeEntity
     */
    public ShopOrder getShopOrderWithDiscountCode(DiscountCodeEntity discountCodeEntity) {
        TypedQuery<ShopOrder> query = entityManager.createQuery("select o from ShopOrder as o where discountCode.id = " +
                ":discountCodeId", ShopOrder.class);
        query.setParameter("discountCodeId", discountCodeEntity.getId());

        return query.getSingleResult();
    }

    /**
     * Finds a ShopOrder with the given PayJohnson id (matching between ShopOrder and PayJohnson Transaction)
     * @param id payJohnsonId
     * @return ShopOrder with given payJohnson id
     */
    public ShopOrder getWithTransactionId(String id) {
        TypedQuery<ShopOrder> query = entityManager.createQuery("select o from ShopOrder as o where " +
                "shopTransaction.transactionId = :transactionId", ShopOrder.class);
        query.setParameter("transactionId", id);

        return query.getSingleResult();
    }
}
