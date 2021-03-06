package de.ShopJohnson.sw.entity.repo;

import de.ShopJohnson.sw.entity.Article;
import de.ShopJohnson.sw.entity.util.SingleIdEntityRepository;

import javax.enterprise.context.Dependent;


/**
 * Repository for Article Entities
 */
@Dependent
public class ArticleRepo extends SingleIdEntityRepository<Long, Article> {

}
