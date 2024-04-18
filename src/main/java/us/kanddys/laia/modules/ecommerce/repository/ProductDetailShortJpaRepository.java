package us.kanddys.laia.modules.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.ProductDetailShort;

@Repository
public interface ProductDetailShortJpaRepository extends JpaRepository<ProductDetailShort, Long> {
   
   public Optional<ProductDetailShort> findProductDetailsByProductId(Long productId);
}
