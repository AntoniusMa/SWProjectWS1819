package de.ShopJohnson.sw.ui.model;

import de.ShopJohnson.sw.Emeddables.ShopTransaction;
import de.ShopJohnson.sw.JohnonConfig;
import de.ShopJohnson.sw.service.ShopOrderService;
import de.ShopJohnson.sw.service.alternatives.AbstractPayService;
import de.ShopJohnson.sw.ui.consts.TransactionStatus;
import de.ShopJohsnon.sw.entity.Article;
import de.ShopJohsnon.sw.entity.ShopOrder;
import org.primefaces.PrimeFaces;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import org.jboss.logging.Logger;

@Named
@SessionScoped
public class CartModel implements Serializable {
    private String payJohnsonName;
    private ShopOrder shopOrder;
    private String discountCode;
    private float discountFactor = 1.0f;

    private String discountMeme = "";
    @Inject UserModel userModel;



    @Inject
    AbstractPayService payService;

    @Inject
    ShopOrderService shopOrderService;

    @Inject
    Logger logger;


    public CartModel() {
        this.shopOrder = new ShopOrder(new ArrayList<Article>());
    }
    public void addArticleToCart(Article a) {
        shopOrder.addArticle(a);
        logger.info("Adding article");
    }
    public void removeArticleFromCart(Article a) {
        shopOrder.removeArticle(a);
    }
    public void buy() {
        PrimeFaces pf = PrimeFaces.current();
//        if(!userModel.isLoggedIn()) {
//            return;
//        }
        shopOrder.setCustomer(userModel.getCustomer());
        ShopTransaction shopTransaction = new ShopTransaction(JohnonConfig.SHOP_PAYJOHNSON_ID, TransactionStatus.TRANSACTION_NOT_CONFIRMED);
        shopTransaction.setPayStatus(payService.instructJohnsonPayment(shopTransaction));
        if(shopTransaction.getPayStatus() == TransactionStatus.TRANSACTION_DATA_CONFIRMED) {
            shopOrderService.persistShopOrder(shopOrder);
        }

        pf.executeScript("PF('pay_dialog_success').show()");
    }
    public void applyDiscountCode() {
        if(discountFactor < 1 ) {
            discountMeme = "https://www.meme-arsenal.com/memes/12382393052b1ef12badff29cf602857.jpg";
        }
        else {
            discountFactor = 0.5f;
            discountMeme = "https://pbs.twimg.com/media/DrB6e12WoAAUjUg.jpg";
        }
    }
    public void hideDiscountDialog() {
        PrimeFaces pf = PrimeFaces.current();
        pf.executeScript("PF('discount_dialog').hide()");
    }
    public String getPayJohnsonConfirmation() {

        return null;
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

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
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
}
