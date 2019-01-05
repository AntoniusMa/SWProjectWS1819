package de.ShopJohnson.sw.ui.model;

import de.ShopJohnson.sw.service.ArticleService;
import de.ShopJohsnon.sw.entity.Article;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ApplicationScoped
public class ShopModel implements Serializable {
    @Inject
    private ArticleService articleService;

    private List<Article> articleList;

    public void fillEmptyDataBase() {

    }
}
