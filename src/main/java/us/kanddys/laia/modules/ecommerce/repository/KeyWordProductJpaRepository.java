package us.kanddys.laia.modules.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.KeyWordProduct;
import us.kanddys.laia.modules.ecommerce.model.KeyWordProductId;

@Repository
public interface KeyWordProductJpaRepository extends JpaRepository<KeyWordProduct, KeyWordProductId> {

   @Query(value = "SELECT COUNT(*) FROM keywords_products WHERE key_word_id IN (:wordIds)", nativeQuery = true)
   public List<Integer> countKeyWordProductByWordIds(@Param("wordIds") List<Long> wordIds);

   @Query(value = "SELECT COUNT(*) FROM keywords_products WHERE product_id = ?1", nativeQuery = true)
   public Integer countKeyWordProductByProductId(Long productId);
}
