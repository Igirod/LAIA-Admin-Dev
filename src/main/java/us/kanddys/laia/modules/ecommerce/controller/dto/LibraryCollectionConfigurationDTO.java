package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase contiene los métodos y atributos necesarios para representar un
 * data transfer object (DTO) de la configuración de una colección de la
 * biblioteca.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
public class LibraryCollectionConfigurationDTO {
   @JsonProperty
   private String title;
   @JsonProperty
   private Integer conf;
   @JsonProperty
   private Integer ascDsc;
   @JsonProperty
   private String order;
   @JsonProperty("filter")
   private LibraryCollectionFilterDTO filter;
   @JsonProperty
   private Integer operation;

   public LibraryCollectionConfigurationDTO() {
   }
}
