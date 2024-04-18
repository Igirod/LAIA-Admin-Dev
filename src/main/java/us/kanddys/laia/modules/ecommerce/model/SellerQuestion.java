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
 * Esta clase representa una pregunta que un usuario puede hacer a un vendedor.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
@Table(name = "sellers_questions")
@Entity
public class SellerQuestion {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;
   @Column(name = "question")
   private String question;
   @Column(name = "required")
   private Integer required;
   @Column(name = "type")
   private String type;
   @Column(name = "limit_value")
   private Integer limit;
   @Column(name = "product_id")
   private Long productId;

   public SellerQuestion() {
   }
}
