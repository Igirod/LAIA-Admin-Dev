package us.kanddys.laia.modules.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.SellerQuestionMultipleOption;

@Repository
public interface SellerQuestionMultipleOptionJpaRepository extends JpaRepository<SellerQuestionMultipleOption, Long> {

   @Modifying
   @Query(value = "DELETE FROM sellers_questions_multiples_options WHERE seller_question_id = ?1", nativeQuery = true)
   public void deleteBySellerQuestionId(Long sellerQuestionId);

   @Query(value = "SELECT value FROM sellers_questions_multiples_options WHERE seller_question_id = ?1", nativeQuery = true)
   public List<String> findBySellerQuestionId(Long sellerQuestionId);
}
