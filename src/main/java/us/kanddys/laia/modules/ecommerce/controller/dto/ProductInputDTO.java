package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase representa un data transfer object (DTO) de un producto de
 * entrada.
 *
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
public class ProductInputDTO {
   @JsonProperty
   private Long productId;
   @JsonProperty
   private Integer quantity;
   @JsonProperty
   private Double price;
   @JsonProperty
   private String image;
}
