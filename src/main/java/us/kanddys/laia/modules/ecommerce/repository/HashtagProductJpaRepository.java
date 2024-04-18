package us.kanddys.laia.modules.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.HashtagProduct;
import us.kanddys.laia.modules.ecommerce.model.HashtagProductId;

@Repository
public interface HashtagProductJpaRepository extends JpaRepository<HashtagProduct, HashtagProductId> {

   @Query(value = "SELECT hash_id FROM hashtags_products WHERE product_id = ?1", nativeQuery = true)
   public Long findByProductId(Long productId);
}
