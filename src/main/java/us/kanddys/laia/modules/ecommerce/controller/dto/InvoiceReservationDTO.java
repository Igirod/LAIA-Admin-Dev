package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class InvoiceReservationDTO {

   @JsonProperty
   private Long batchId;
   @JsonProperty
   private String batchFrom;
   @JsonProperty
   private String batchTo;
   @JsonProperty
   private String reservation;
   @JsonProperty
   private String tDelay;
   @JsonProperty
   private Integer delay;
}
