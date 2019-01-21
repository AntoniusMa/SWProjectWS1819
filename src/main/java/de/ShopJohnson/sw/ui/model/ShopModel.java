package de.ShopJohnson.sw.ui.model;

import de.ShopJohnson.sw.service.ArticleService;
import de.ShopJohnson.sw.entity.Article;
import org.jboss.logging.Logger;


import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class ShopModel implements Serializable {
    @Inject
    private ArticleService articleService;

    private List<Article> articleList;

    @Inject
    Logger logger;

    /**
     * Fills empty DB with example articles and redirects to shop page
     * @return shop.xthml
     */
    public String fillEmptyDataBase() {
        if (articleService.getAllArticles().isEmpty()) {
            articleService.addArticleToRange(new Article("Lasersword chopsticks", 19.99f, "Chopsticks in Lasersword optic",
                    "https://cdn.shopify.com/s/files/1/2212/5665/products/inspire-uplift-home-kitchen-red-lightsaber-chopsticks-32834872395_1000x.jpg?v=1538968279"));
            articleService.addArticleToRange(new Article("Broc", 5000f, "Delicious broc",
                "https://img.rewe-static.de/1007433/21403678_digital-image.png?resize=300px:300px&background-color=FFFFFF"));
            articleService.addArticleToRange(new Article("Ultra Slut", 9.50f, "Ultra Slut Body Detergent",
                    "http://www.vitamin-ha.com/wp-content/uploads/2013/05/Funny-Creative-Products-15.jpg"));
            articleService.addArticleToRange(new Article("Jabba Mug", 35f, "Jabba The Hutt Coffee Mug",
                    "https://awesomestufftobuy.com/wp-content/uploads/2018/11/jabba-the-hutt-coffee-mug-3-550x550.jpg"));
            articleService.addArticleToRange(new Article("Penguin", 5f, "Stupidest animal",
                    "https://sadanduseless.b-cdn.net/wp-content/uploads/2018/09/funny-fake-product4.jpg"));
            articleService.addArticleToRange(new Article("Cooking with Poo", 10f, "Thai cooking book",
                    "https://static.bangkokpost.com/media/content/20180309/2677743.jpg"));
            articleService.addArticleToRange(new Article("Replacement bubble for water level", 3f, "",
                    "https://etel-tuning.eu/459-large_default/ersatzblase-fur-wasserwaage.jpg"));
            articleService.addArticleToRange(new Article("Gearbox Sand", 10.50f, "",
                    "https://etel-tuning.eu/385-home_default/getriebesand.jpg"));
            articleService.addArticleToRange(new Article("Sonic Obama Backpack", 50f, "",
                    "http://1.media.collegehumor.cvcdn.com/37/25/188bd53a49d5184a37e901cfea581969.jpg"));
            logger.info("Added articles to database");
        }
        logger.info("Articles are already in database");
        return "shop.xhtml?faces-redirect=true";
    }

    /**
     * Loads all articles
     */
    @PostConstruct
    void loadArticles() {
        this.articleList = articleService.getAllArticles();
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }
}
