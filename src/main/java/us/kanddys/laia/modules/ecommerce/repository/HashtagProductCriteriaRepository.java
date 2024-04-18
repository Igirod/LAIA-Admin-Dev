package us.kanddys.laia.modules.ecommerce.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import us.kanddys.laia.modules.ecommerce.model.HashtagProduct;

@Repository
public class HashtagProductCriteriaRepository {

   @Autowired
   private EntityManager entityManager;

   public Long getHashtagIdsByProductId(Long productId) {
      List<Predicate> predicates = new ArrayList<>();
      CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
      CriteriaQuery<Long> cQueryHashtagProduct = cBuilder.createQuery(Long.class);
      Root<HashtagProduct> rHashtagProduct = cQueryHashtagProduct.from(HashtagProduct.class);
      predicates.add(cBuilder.equal(rHashtagProduct.get("id").get("productId"), productId));
      cQueryHashtagProduct.select(rHashtagProduct.get("id").get("hashId"));
      cQueryHashtagProduct.where(predicates.toArray(new Predicate[0]));
      return entityManager.createQuery(cQueryHashtagProduct).getSingleResult();
   }

   @Transactional
   public int deleteHashtagProductsByProductId(Long productId) {
      CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
      CriteriaDelete<HashtagProduct> criteriaDelete = criteriaBuilder.createCriteriaDelete(HashtagProduct.class);
      Root<HashtagProduct> root = criteriaDelete.from(HashtagProduct.class);
      criteriaDelete.where(criteriaBuilder.equal(root.get("id").get("productId"), productId));
      int deletedCount = entityManager.createQuery(criteriaDelete).executeUpdate();
      return deletedCount;
   }
}
