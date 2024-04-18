package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase representa un data transfer object (DTO) para la respuesta del
 * servicio de checkeo de email.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
public class RCheckEmailDTO {

   @JsonProperty
   private Integer status;
   @JsonProperty
   private Long userId;
   @JsonProperty
   private String userName;
   @JsonProperty
   private String userLastName;

   public RCheckEmailDTO() {
   }
}
