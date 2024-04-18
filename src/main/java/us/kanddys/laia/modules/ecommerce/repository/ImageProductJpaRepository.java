package us.kanddys.laia.modules.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.ImageProduct;

@Repository
public interface ImageProductJpaRepository extends JpaRepository<ImageProduct, Long> {

   public List<ImageProduct> findAllByProductId(Long productId);

   @Query(value = "SELECT MAX(id) FROM ImageProduct")
   public Long findMaxImageProductId();
}
