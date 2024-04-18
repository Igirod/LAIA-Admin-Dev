package us.kanddys.laia.modules.ecommerce.controller.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CombinedProductDTO {
   @JsonProperty
   private Long merchantId;
   @JsonProperty
   private String merchantTitle;
   @JsonProperty
   private Long productId;
   @JsonProperty
   private String productTitle;
   @JsonProperty
   private Double productPrice;
   @JsonProperty
   private String productFrontPage;
   @JsonProperty
   private List<ImageProductDTO> images;
   @JsonProperty
   private List<ProductDetailDTO> details;
   @JsonProperty
   private Integer productStock;
   @JsonProperty
   private Long invoiceId;
   @JsonProperty
   private Integer invoiceCount;
   @JsonProperty
   private Long userId;
   @JsonProperty
   private Integer check;
   @JsonProperty
   private String firstShippingDate;
   @JsonProperty
   private String merchantDirection;
   @JsonProperty
   private Long batchId;
   @JsonProperty
   private String batchFrom;
   @JsonProperty
   private String batchTo;

   public CombinedProductDTO() {
   }
}
