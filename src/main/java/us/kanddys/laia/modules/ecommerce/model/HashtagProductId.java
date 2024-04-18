package us.kanddys.laia.modules.ecommerce.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Embeddable
@Data
@AllArgsConstructor
public class HashtagProductId {

   private Long hashId;
   private Long productId;

   public HashtagProductId() {
   }
}
