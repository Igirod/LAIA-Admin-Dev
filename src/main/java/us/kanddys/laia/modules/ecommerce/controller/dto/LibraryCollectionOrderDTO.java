package us.kanddys.laia.modules.ecommerce.controller.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase contiene los métodos y atributos necesarios para representar un
 * data transfer object (DTO) de una orden de una colección de la biblioteca.
 * 
 * @author Igirod0
 * @version 1.0.1
 */
@AllArgsConstructor
@Data
public class LibraryCollectionOrderDTO {
   @JsonProperty
   private String title;
   @JsonProperty
   private Integer ascDsc;
   @JsonProperty
   private Integer operation;
   @JsonProperty
   private Set<String> orderProps;

   public LibraryCollectionOrderDTO() {
   }
}
