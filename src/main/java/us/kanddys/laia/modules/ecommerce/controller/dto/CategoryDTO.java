package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase representa un data transfer object de la entidad Category.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
public class CategoryDTO {
   @JsonProperty
   private Long id;
   @JsonProperty
   private String title;

   public CategoryDTO() {
   }
}
