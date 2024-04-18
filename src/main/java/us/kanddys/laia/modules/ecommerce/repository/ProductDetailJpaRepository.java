package us.kanddys.laia.modules.ecommerce.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.ProductDetail;

@Repository
public interface ProductDetailJpaRepository extends JpaRepository<ProductDetail, Long> {

   public List<ProductDetail> findProductDetailsByProductId(Long productId);

   @Query(value = "SELECT COUNT(*) FROM products_details WHERE product_id = ?1", nativeQuery = true)
   public Integer countProductDetailsByProductId(Long productId);

   @Query(value = "SELECT url, type FROM products_details WHERE product_id = ?1 ORDER BY id DESC LIMIT 1", nativeQuery = true)
   public Map<String, Object> findLastProductDetailByProductId(Long productId);
}
