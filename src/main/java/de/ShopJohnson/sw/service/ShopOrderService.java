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
    public ShopOrder createShopOrder(Customer customer, List<Article> articles) {
        customer = customerService.getCustomerById(customer.getCustomerId());
        customer = entityManager.merge(customer);

        ShopOrder shopOrder = new ShopOrder(customer, articles);
        customer.addShopOrder(shopOrder);
        entityManager.persist(shopOrder);
        entityManager.persist(customer);
        return shopOrder;
    }
    public List<ShopOrder> getCustomersOrders(Customer customer) {
        TypedQuery<ShopOrder> query = entityManager.createQuery(
                "select o from ShopOrder as o where o.customer = :customer", ShopOrder.class
        );
        query.setParameter("customer", customer);
        return  query.getResultList();
    }
}
