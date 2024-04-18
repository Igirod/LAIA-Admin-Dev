package us.kanddys.laia.modules.ecommerce.controller.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class InvoiceRBodyDTO {
   @JsonProperty
   private Long userId;
   @JsonProperty
   private String email;
   @JsonProperty
   private String phone;
   @JsonProperty
   private String name;
   @JsonProperty
   private String surname;
   @JsonProperty
   private String reservation;
   @JsonProperty
   private String reservationType;
   @JsonProperty
   private Long merchantId;
   @JsonProperty
   private String message;
   @JsonProperty
   private Long batchId;
   @JsonProperty(value = "articles")
   private List<InvoiceArticleInputDTO> articles;
   @JsonProperty(value = "address")
   private InvoiceAddressInputDTO address;

   public InvoiceRBodyDTO() {
   }
}
