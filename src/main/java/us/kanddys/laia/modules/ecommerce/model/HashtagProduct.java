package us.kanddys.laia.modules.ecommerce.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase representa un hashtag asociado a un producto.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
@Table(name = "hashtags_products")
@Entity
public class HashtagProduct {

   @EmbeddedId
   private HashtagProductId id;

   public HashtagProduct() {
   }
}
