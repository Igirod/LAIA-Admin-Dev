package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase contiene los datos de un producto de una orden.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
public class OrderProductDTO {
   @JsonProperty
   private Long productId;
   @JsonProperty
   private Long orderId;
   @JsonProperty
   private Integer quantity;
   @JsonProperty
   private ProductDTO product;

   public OrderProductDTO() {
   }
}
