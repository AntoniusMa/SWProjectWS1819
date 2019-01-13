package de.ShopJohnson.sw.service;

import de.ShopJohnson.sw.JohnonConfig;
import de.ShopJohsnon.sw.entity.Article;
import de.ShopJohsnon.sw.entity.Customer;
import de.ShopJohsnon.sw.entity.ShopOrder;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class ShopOrderService {
    @PersistenceContext(unitName = JohnonConfig.PERSISTANCE_UNIT_NAME)
    private EntityManager entityManager;

    @Inject
    CustomerService customerService;


    @Transactional
    public ShopOrder persistShopOrder(ShopOrder shopOrder) {
        shopOrder.setCustomer(customerService.getCustomerByName(shopOrder.getCustomer().getUsername()));
        shopOrder.getCustomer().addShopOrder(shopOrder);
        entityManager.persist(shopOrder);
        Customer c = customerService.persist(shopOrder.getCustomer());
        System.out.println(c.getShopOrders().isEmpty());
        return shopOrder;
    }
    public List<ShopOrder> getCustomersOrders(Customer customer) {
        TypedQuery<ShopOrder> query = entityManager.createQuery(
                "select o from ShopOrder as o where o.customer.id = :customer", ShopOrder.class
        );
        query.setParameter("customer", customer.getId());
        return  query.getResultList();
    }
}
