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

    /**
     * Adds article to range of articles in ShopJohnson
     * @param a Article to add
     * @return persisted Article
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Article addArticleToRange(Article a) {
        articleRepo.persist(a);
        return a;
    }

    /**
     * Removes article from range of articles in ShopJohnson
     * @param a article to remove
     * @return removed article
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Article removeArticleFromRange(Article a){
        a = articleRepo.getById(a.getId());

        articleRepo.remove(a);

        return a;
    }

    /**
     * Change data for an article
     * @param newArticleData article to change
     * @return changed article
     */
    public Article changeArticleData(Article newArticleData) {
        Article original = articleRepo.getById(newArticleData.getId());
        original.setArtName(newArticleData.getArtName());
        original.setDescription(newArticleData.getDescription());
        original.setPrice(newArticleData.getPrice());
        original.setPicturePath(newArticleData.getPicturePath());

        return original;
    }

    /**
     * Get article by article id
     * @param artNr id of the article
     * @return found article
     */
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
