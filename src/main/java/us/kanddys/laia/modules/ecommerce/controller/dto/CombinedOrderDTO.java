package us.kanddys.laia.modules.ecommerce.controller.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CombinedOrderDTO {
   @JsonProperty
   private Long merchantId;
   @JsonProperty
   private List<ProductDTO> products;
   @JsonProperty
   private InvoiceDTO invoice;
   @JsonProperty
   private Long userId;
}
