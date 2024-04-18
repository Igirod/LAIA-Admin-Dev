package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase contiene los atributos y métodos necesarios para representar un
 * data transfer object (DTO) de la configuración de una biblioteca.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
public class LibraryConfigDTO {
   @JsonProperty
   private String title;
   @JsonProperty
   private Integer type;
   @JsonProperty
   private Integer conf;
   @JsonProperty
   private Integer operation;

   public LibraryConfigDTO() {
   }
}
