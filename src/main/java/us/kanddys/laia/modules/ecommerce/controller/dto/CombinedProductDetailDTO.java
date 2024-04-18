package us.kanddys.laia.modules.ecommerce.controller.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase representa un producto completo junto a su detalle.
 * 
 * @author Igirod0
 * @version 1.0.1
 */
@AllArgsConstructor
@Data
public class CombinedProductDetailDTO {
   @JsonProperty
   private Integer stock;
   @JsonProperty
   private Integer check;
   @JsonProperty
   private String merchantDirection;
   @JsonProperty
   private String firstShippingDate;
   @JsonProperty
   private Long batchId;
   @JsonProperty
   private String batchFrom;
   @JsonProperty
   private String batchTo;
   @JsonProperty
   private List<ImageProductDTO> images;
   @JsonProperty
   private List<ProductDetailDTO> details;
}
