package us.kanddys.laia.modules.ecommerce.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase representa una clave primaria de una relación entre una categoría
 * y un producto.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Embeddable
@AllArgsConstructor
@Data
public class CategoryProductId {

   private Long categoryId;
   private Long productId;

   public CategoryProductId() {
   }
}
