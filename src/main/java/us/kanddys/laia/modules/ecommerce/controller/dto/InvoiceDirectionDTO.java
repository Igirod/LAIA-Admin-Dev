package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class InvoiceDirectionDTO {
   @JsonProperty
   private String code;
   @JsonProperty
   private String country;
   @JsonProperty
   private String state;
   @JsonProperty
   private String city;
   @JsonProperty
   private String street;
   @JsonProperty
   private String number;
   @JsonProperty
   private String ref;
   @JsonProperty
   private String note;
   @JsonProperty
   private String lat;
   @JsonProperty
   private String lon;

   public InvoiceDirectionDTO() {
   };
}
