package us.kanddys.laia.modules.ecommerce.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Embeddable
@Data
@AllArgsConstructor
public class OrderProductId {

   private Long productId;
   private Long orderId;

   public OrderProductId() {
   }
}
