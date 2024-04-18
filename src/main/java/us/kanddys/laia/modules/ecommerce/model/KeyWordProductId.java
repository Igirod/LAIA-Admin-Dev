package us.kanddys.laia.modules.ecommerce.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase representa la clave primaria de la clase KeyWordProduct.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Embeddable
@Data
@AllArgsConstructor
public class KeyWordProductId {

   private Long keyWordId;
   private Long productId;

   public KeyWordProductId() {
   }
}
