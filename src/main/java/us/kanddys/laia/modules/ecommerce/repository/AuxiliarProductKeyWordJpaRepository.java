package us.kanddys.laia.modules.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.AuxiliarProductKeyWord;

@Repository
public interface AuxiliarProductKeyWordJpaRepository extends JpaRepository<AuxiliarProductKeyWord, Long> {

   @Query(value = "SELECT word FROM aux_products_key_words WHERE aux_product_id = ?1", nativeQuery = true)
   List<String> findByAuxiliarProductId(Long auxProductId);

   @Modifying
   @Query(value = "DELETE FROM aux_products_key_words WHERE aux_product_id = ?1", nativeQuery = true)
   void deleteWordsByProductId(Long productId);
}
