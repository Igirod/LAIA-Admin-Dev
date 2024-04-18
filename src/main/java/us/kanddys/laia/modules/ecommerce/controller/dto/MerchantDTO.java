package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase contiene los atributos y métodos necesarios para representar un
 * data transfer object (DTO) de un comerciante.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
public class MerchantDTO {
   @JsonProperty
   private Long id;
   @JsonProperty
   private String title;
   @JsonProperty
   private String email;
   @JsonProperty
   private String phone;

   public MerchantDTO() {
   };
}
