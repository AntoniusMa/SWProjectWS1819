package de.ShopJohnson.sw.service;

import de.ShopJohsnon.sw.entity.Article;
import de.ShopJohsnon.sw.entity.repo.ArticleRepo;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class ArticleService {

//    @PersistenceContext(unitName = "examplePU")
//    private EntityManager entityManager;
    @Inject
    ArticleRepo articleRepo;

    @Transactional(Transactional.TxType.REQUIRED)
    public Article addArticleToRange(Article a) {
        articleRepo.persist(a);
        return a;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Article removeArticleFromRange(Article a){
        a = articleRepo.merge(a);

        articleRepo.remove(a);

        return a;
    }

    public Article changeArticleData(Article newArticleData) {
        newArticleData =articleRepo.merge(newArticleData);

        return newArticleData;
    }
    public Article getArticleById(long artNr){
        Article found = articleRepo.getById(artNr);
        return found;
    }
    public List<Article> getAllArticles() {
//        TypedQuery<Article> query = entityManager.createQuery(
//                "SELECT a FROM Article AS a",
//                Article.class
//        );
        return articleRepo.getAll();
    }
}
