package us.kanddys.laia.modules.ecommerce.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import us.kanddys.laia.modules.ecommerce.model.KeyWord;

@Repository
public class KeyWordCriteriaRepository {

   @Autowired
   private EntityManager entityManager;

   public List<String> findKeywordsByUserIdAndValue(Long userId, Optional<String> value) {
      List<Predicate> predicates = new ArrayList<>();
      CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
      CriteriaQuery<String> cQueryValue = cBuilder.createQuery(String.class);
      Root<KeyWord> rKeyword = cQueryValue.from(KeyWord.class);
      cQueryValue.select(rKeyword.get("word"));
      predicates.add(cBuilder.equal(rKeyword.get("userId"), userId));
      if (value.isPresent())
         predicates.add(cBuilder.like(rKeyword.get("word"), "%" + value.get() + "%"));
      cQueryValue.where(predicates.toArray(new Predicate[0]));
      return entityManager.createQuery(cQueryValue).setMaxResults(5).getResultList();
   }
}
