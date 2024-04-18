package us.kanddys.laia.modules.ecommerce.controller.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MerchantProductInvoiceDTO {
   @JsonProperty
   private Long merchantId;
   @JsonProperty
   private String merchantTitle;
   @JsonProperty
   private List<ProductDTO> products;
}
