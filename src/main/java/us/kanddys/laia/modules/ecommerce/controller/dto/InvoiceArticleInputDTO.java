package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase representa un data transfer object (DTO) para los artículos de una
 * factura.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
public class InvoiceArticleInputDTO {
   @JsonProperty
   private Long productId;
   @JsonProperty
   private Integer quantity;

   public InvoiceArticleInputDTO() {
   }
}
