package de.ShopJohnson.sw.ui.converter;


import de.ShopJohnson.sw.entity.ShopOrder;
import de.ShopJohnson.sw.service.ShopOrderService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 * Converter for ShopOrder entities
 * This will Convert a ShopOrder to a String that displays the Order id, billing amount and pay status and vice versa
 * This is not really necessary but implemented to show that the concept of a converter is understood
 */
@FacesConverter("ShopOrderConverter")
public class ShopOrderConverter implements Converter {

    @Inject
    ShopOrderService shopOrderService;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (s.isEmpty()) {
           return null;
        }
        String[] splittedString = s.split(" ");
        if(!splittedString[0].equals("Order number:")) {
            return null;
        }
        long id = Long.parseLong(splittedString[1]);
        return shopOrderService.getById(id);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o == null) {
            return "";
        }
        if (!(o instanceof ShopOrder)) {
           return "";
        }
        StringBuilder sb = new StringBuilder();
        ShopOrder so = (ShopOrder) o;
        sb.append("Order number: ");
        sb.append((so.getOrderId()));
        sb.append(" Amount: ");
        sb.append(so.getBillingAmount());
        sb.append(" Pay status: ");
        sb.append(so.getShopTransaction().getPayStatus());

        return sb.toString();
    }
}
