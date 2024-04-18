package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase contiene los atributos y métodos necesarios para representar un
 * data transfer object (DTO) de una miniatura de biblioteca.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
public class LibraryMiniatureDTO {
   @JsonProperty
   private String title;
   @JsonProperty
   private String[] miniatureProps;
   @JsonProperty
   private String[] miniatureHeader;
   @JsonProperty
   private String[] miniatureTitle;
   @JsonProperty
   private String[] miniatureSubtitle;
   @JsonProperty
   private Integer operation;

   public LibraryMiniatureDTO() {
   }
}
