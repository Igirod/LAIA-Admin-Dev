package us.kanddys.laia.modules.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.AuxiliarProductMedia;

@Repository
public interface AuxiliarProductMediaJpaRepository extends JpaRepository<AuxiliarProductMedia, Long> {

   @Modifying
   @Query(value = "DELETE FROM aux_products_medias WHERE aux_product_id = ?1", nativeQuery = true)
   void deleteByAuxiliarProductId(Long auxiliarProductId);
}
