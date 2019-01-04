package de.ShopJohsnon.sw.entity.repo;

import de.ShopJohsnon.sw.entity.Article;
import de.ShopJohsnon.sw.entity.util.SingleIdEntityRepository;

import javax.enterprise.context.Dependent;

@Dependent
public class ArticleRepo extends SingleIdEntityRepository<Long, Article> {

}
