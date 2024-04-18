package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase representa el data transfer object (DTO) de la respuesta de la
 * operación loginUser.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
public class RLoginUserDTO {

   @JsonProperty
   private Integer phone;
   @JsonProperty
   private String image;
   @JsonProperty
   private Integer status;

   public RLoginUserDTO() {
   }
}
