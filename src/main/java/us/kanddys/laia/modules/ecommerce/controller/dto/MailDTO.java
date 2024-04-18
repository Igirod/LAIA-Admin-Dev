package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MailDTO {
   @JsonProperty
   private String to;
   @JsonProperty
   private String subject;
   @JsonProperty
   private String text;
   @JsonProperty
   private String accessUrl;
   @JsonProperty
   private Long invoiceId;
}
