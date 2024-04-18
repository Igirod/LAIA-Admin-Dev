package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase contiene los atributos y métodos necesarios para representar un
 * data transfer object (DTO) de una miniatura que pertenece a una biblioteca.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
public class LibraryCollectionMiniatureDTO {
   @JsonProperty
   private String title;
   @JsonProperty
   private Integer operation;
   @JsonProperty("collection")
   private CollectionMiniatureDTO collectionMiniature;

   public LibraryCollectionMiniatureDTO() {
   }
}
