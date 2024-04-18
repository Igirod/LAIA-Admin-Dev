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
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
@Table(name = "aux_products_key_words")
@Entity
public class AuxiliarProductKeyWord {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "aux_product_key_word_id")
   private Long id;
   @Column(name = "aux_product_id")
   private Long auxProductId;
   @Column(name = "word")
   private String word;

   public AuxiliarProductKeyWord() {
   }
}
