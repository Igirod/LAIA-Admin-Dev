package us.kanddys.laia.modules.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Table(name = "aux_multiple_options_questions")
@Entity
public class AuxiliarMultipleOptionQuestion {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "aux_multiple_option_question_id")
   private Long auxMultipleOptionQuestionId;
   @Column(name = "aux_product_id")
   private Long auxProductId;
   @Column(name = "value")
   private String value;

   public AuxiliarMultipleOptionQuestion() {
   }
}
