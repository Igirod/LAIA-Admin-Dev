package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class InvoiceCalendarDTO {
   @JsonProperty
   private String tReservation;
   @JsonProperty
   private String reservation;
   @JsonProperty
   private String from;
   @JsonProperty
   private String to;

   public InvoiceCalendarDTO() {
   };
}
