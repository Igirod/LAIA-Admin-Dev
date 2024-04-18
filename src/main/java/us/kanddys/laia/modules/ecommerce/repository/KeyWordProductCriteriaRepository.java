package us.kanddys.laia.modules.ecommerce.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import us.kanddys.laia.modules.ecommerce.model.KeyWordProduct;

@Repository
public class KeyWordProductCriteriaRepository {

   @Autowired
   private EntityManager entityManager;

   public List<Long> findKeywordsProductsIdsByProductId(Long productId) {
      List<Predicate> predicates = new ArrayList<>();
      CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
      CriteriaQuery<Long> cQueryKeyWordProduct = cBuilder.createQuery(Long.class);
      Root<KeyWordProduct> rKeywordProduct = cQueryKeyWordProduct.from(KeyWordProduct.class);
      predicates.add(cBuilder.equal(rKeywordProduct.get("id").get("productId"), productId));
      cQueryKeyWordProduct.select(rKeywordProduct.get("id").get("keyWordId"))
            .where(predicates.toArray(new Predicate[0]));
      return entityManager.createQuery(cQueryKeyWordProduct).getResultList();
   }

   
}
