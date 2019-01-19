package de.ShopJohnson.sw.ui.model;

import de.ShopJohnson.sw.DTO.DiscountCode;
import de.ShopJohnson.sw.Emeddables.ShopTransaction;
import de.ShopJohnson.sw.JohnonConfig;
import de.ShopJohnson.sw.entity.DiscountCodeEntity;
import de.ShopJohnson.sw.entity.repo.DiscountCodeRepo;
import de.ShopJohnson.sw.service.ShopOrderService;
import de.ShopJohnson.sw.service.alternatives.AbstractPayService;
import de.ShopJohnson.sw.ui.consts.TransactionStatus;
import de.ShopJohnson.sw.entity.Article;
import de.ShopJohnson.sw.entity.ShopOrder;
import org.primefaces.PrimeFaces;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import org.jboss.logging.Logger;

@Named
@SessionScoped
public class CartModel implements Serializable {

    private String payJohnsonName;

    private String payJohnsonPw;

    private ShopOrder shopOrder;

    private String discountCodeInput;

    private float discountFactor = 1.0f;

    private String discountMeme = "";

    @Inject UserModel userModel;

    @Inject
    AbstractPayService payService;

    @Inject
    ShopOrderService shopOrderService;

    @Inject
    DiscountCodeRepo discountCodeRepo;

    @Inject
    Logger logger;


    public CartModel() {
        this.shopOrder = new ShopOrder(new ArrayList<Article>());
    }

    /**
     * Adds article to shopping cart
     * @param a Article to add
     */
    public void addArticleToCart(Article a) {
        logger.info("Adding article");
        shopOrder.addArticle(a);
    }

    /**
     * Remove article from shopping cart
     * @param a Article to remove
     */
    public void removeArticleFromCart(Article a) {
        shopOrder.removeArticle(a);
    }

    /**
     * Final confirmation to order
     */
    public void buy() {
        PrimeFaces pf = PrimeFaces.current();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesMessage.Severity messageSeverity = FacesMessage.SEVERITY_ERROR;
        String messageSummary = "Could not submit order";
        String messageDetail = "";


        if(!userModel.isLoggedIn()) {
            return;
        }
        shopOrder.setCustomer(userModel.getCustomer());
        ShopTransaction shopTransaction = new ShopTransaction(payJohnsonName, TransactionStatus.TRANSACTION_NOT_CONFIRMED);
        shopOrder.setShopTransaction(shopTransaction);
        String status = payService.instructJohnsonPayment(shopOrder, discountFactor, payJohnsonName, payJohnsonPw);
        if(status.equals(TransactionStatus.TRANSACTION_DATA_CONFIRMED)) {
            shopOrder.setBillingAmount(shopOrder.getBillingAmount() * discountFactor);
            shopOrder.getShopTransaction().setPayStatus(status);
            shopOrderService.persistShopOrder(shopOrder);
            pf.executeScript("PF('pay_dialog_success').show()");
        }
        else {
            if (status.equals(TransactionStatus.TRANSACTION_NOT_CONFIRMED)) {
                messageDetail = "Pay Johnson could not confirm your data, check username and password";
            }
            else if (status.equals(TransactionStatus.COULD_NOT_REACH_PAY_JOHNSON)) {
                messageDetail = "Pay Johnson did not respond, please try later";
            }
            facesContext.addMessage(null, new FacesMessage(messageSeverity, messageSummary, messageDetail));
        }
    }

    /**
     * Applys discount code on the current ShopOrder
     */
    public void applyDiscountCode() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesMessage.Severity messageSeverity = FacesMessage.SEVERITY_INFO;
        String messageSummary = "";
        String messageDetail = "";
        if(discountFactor < 1 ) {
            messageSeverity = FacesMessage.SEVERITY_WARN;
            messageDetail = "Failed";
            messageSummary = "Can not apply multiple discount codes";
        }
        else {
            DiscountCodeEntity discountCodeEntity = discountCodeRepo.getById(discountCodeInput);
            if (discountCodeEntity==null){
                messageSeverity = FacesMessage.SEVERITY_ERROR;
                messageSummary = "Failed";
                messageDetail = "The discount code does not exist";
            }
            else if(discountCodeEntity.isValid()) {
                messageSummary = "Success";
                messageDetail = "Successfully applied discount code";

                discountFactor = discountCodeEntity.getAmount();
                shopOrder.setDiscountCode(discountCodeEntity);
            }
            else {
                messageSeverity = FacesMessage.SEVERITY_ERROR;
                messageSummary = "Failed";
                messageDetail = "The discount code is invalid";
                logger.info("Discount Code invalid");
            }
        }

        facesContext.addMessage(null, new FacesMessage(messageSeverity, messageSummary, messageDetail));

    }

    public String backToShop() {
        resetModel();
        return "/shop.xhtml?faces-redirect=true";
    }
    private void resetModel() {
        payJohnsonName = null;
        payJohnsonPw = null;
        discountCodeInput = null;
        shopOrder = new ShopOrder(new ArrayList<Article>());
        discountFactor = 1.0f;
    }

    public String getPayJohnsonName() {
        return payJohnsonName;
    }

    public void setPayJohnsonName(String payJohnsonName) {
        this.payJohnsonName = payJohnsonName;
    }

    public ShopOrder getShopOrder() {
        return shopOrder;
    }

    public void setShopOrder(ShopOrder shopOrder) {
        this.shopOrder = shopOrder;
    }

    public String getDiscountCodeInput() {
        return discountCodeInput;
    }

    public void setDiscountCodeInput(String discountCode) {
        this.discountCodeInput = discountCode;
    }

    public float getDiscountFactor() {
        return discountFactor;
    }

    public void setDiscountFactor(float discountFactor) {
        this.discountFactor = discountFactor;
    }

    public String getDiscountMeme() {
        return discountMeme;
    }

    public void setDiscountMeme(String discountMeme) {
        this.discountMeme = discountMeme;
    }

    public String getPayJohnsonPw() {
        return payJohnsonPw;
    }

    public void setPayJohnsonPw(String payJohnsonPw) {
        this.payJohnsonPw = payJohnsonPw;
    }
}
