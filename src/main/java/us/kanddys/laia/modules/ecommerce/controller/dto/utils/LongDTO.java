package us.kanddys.laia.modules.ecommerce.controller.dto.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase se utiliza para manejar los datos de tipo long en los servicios
 * rest.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
public class LongDTO {
   @JsonProperty
   private Long value;

   public LongDTO() {
   }
}
