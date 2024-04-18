package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase contiene los atributos y métodos necesarios para representar un
 * data transfer object (DTO) de una colección de la biblioteca.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
public class LibraryCollectionFilterDTO {
   @JsonProperty
   private Long id;
   @JsonProperty
   private String alias;

   public LibraryCollectionFilterDTO() {
   }
}
