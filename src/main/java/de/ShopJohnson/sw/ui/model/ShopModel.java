package de.ShopJohnson.sw.ui.model;

import de.ShopJohnson.sw.service.ArticleService;
import de.ShopJohsnon.sw.entity.Article;


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

    /**
     * Function used to fill the database with dummy data
     */
    public void fillEmptyDataBase() {
        for (int i = 0; i < 10; i++) {
            articleService.addArticleToRange(new Article("Nicer artikel" + i, 20f, "Broc",
                                    "https://i.ytimg.com/vi/Ir7UmJ_foHs/hqdefault.jpg"));
        }
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
