package us.kanddys.laia.modules.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase representa una pregunta de un vendedor con multiples opciones.
 * 
 * @autor Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
@Entity
@Table(name = "sellers_questions_multiples_options")
public class SellerQuestionMultipleOption {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(name = "seller_question_id")
   private Long sellerQuestionId;
   @Column(name = "value")
   private String value;

   public SellerQuestionMultipleOption() {
   }
}
