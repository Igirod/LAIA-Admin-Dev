package us.kanddys.laia.modules.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.SellerQuestion;

@Repository
public interface SellerQuestionJpaRepository extends JpaRepository<SellerQuestion, Long> {

   @Query(value = "SELECT id FROM sellers_questions WHERE question = ?1 AND type = ?2", nativeQuery = true)
   public Long findQuestionIdByQuestionAndType(String question, String type);

   @Query(value = "SELECT * FROM sellers_questions WHERE question = ?1 AND type = ?2", nativeQuery = true)
   public SellerQuestion findQuestionByQuestionAndType(String question, String type);

   @Query(value = "SELECT * FROM sellers_questions WHERE product_id = ?1", nativeQuery = true)
   public List<SellerQuestion> findQuestionByProductId(Long productId);

   @Query(value = "SELECT COUNT(*) FROM sellers_questions WHERE product_id = ?1", nativeQuery = true)
   public Integer countQuestionsByProductId(Long productId);
}
