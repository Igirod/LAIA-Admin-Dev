package us.kanddys.laia.modules.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.AuxiliarMultipleOptionQuestion;

@Repository
public interface AuxiliarMultipleQuestionJpaRepository extends JpaRepository<AuxiliarMultipleOptionQuestion, Long> {

   @Query(value = "SELECT value FROM aux_multiple_questions_options WHERE aux_product_id = ?1", nativeQuery = true)
   public List<String> findOptionsByProductId(Long productId);

   @Modifying
   @Query(value = "DELETE FROM aux_multiple_questions_options WHERE aux_product_id = ?1", nativeQuery = true)
   public void deleteOptionsByProductId(Long productId);
}
