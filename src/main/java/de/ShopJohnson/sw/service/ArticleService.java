package de.ShopJohnson.sw.service;

import de.ShopJohsnon.sw.entity.Article;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class ArticleService {

    @PersistenceContext(unitName = "examplePU")
    private EntityManager entityManager;

    @Transactional
    public Article addArticleToRange(Article a) {
        entityManager.persist(a);
        return a;
    }
    @Transactional
    public Article removeArticleFromRange(Article a){
        a = entityManager.merge(a);

        entityManager.remove(a);

        return a;
    }

    public Article changeArticleData(Article newArticleData) {

        newArticleData = entityManager.merge(newArticleData);

        return newArticleData;
    }
    public Article getArticleById(long artNr){
        Article found = entityManager.find(Article.class, artNr);
        return found;
    }
    public List<Article> getAllArticles() {
        TypedQuery<Article> query = entityManager.createQuery(
                "SELECT a FROM Article AS a",
                Article.class
        );
        return query.getResultList();
    }
}
