package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderProductInputDTO {
   @JsonProperty
   private Long invoiceId;
   @JsonProperty
   private Long productId;
   @JsonProperty
   private Integer quantity;
}
