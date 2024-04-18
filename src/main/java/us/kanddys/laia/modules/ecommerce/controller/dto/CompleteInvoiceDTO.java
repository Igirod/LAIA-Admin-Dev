package us.kanddys.laia.modules.ecommerce.controller.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase contiene los atributos y métodos necesarios para representar un
 * data transfer object (DTO) de una factura completa.
 * 
 * @author igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
public class CompleteInvoiceDTO {
   @JsonProperty("cli")
   private CompleteInvoiceClientDTO cli;
   @JsonProperty("merchant")
   private CompleteInvoiceMerchantDTO merchant;
   @JsonProperty("articles")
   private List<CompleteInvoiceArticleDTO> articles;
   @JsonProperty("direction")
   private CompleteInvoiceDirectionDTO direction;
   @JsonProperty("calendar")
   private CompleteInvoiceCalendarDTO calendar;
   @JsonProperty
   private String message;
   @JsonProperty
   private Integer operation;

   public CompleteInvoiceDTO() {
   };
}
