package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase cotiene los atributos y métodos necesarios para representar un
 * data transfer object (DTO) de una miniatura que pertenece a una colección.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
public class CollectionMiniatureDTO {
   @JsonProperty
   private Long id;
   @JsonProperty
   private String[] miniatureProps;
   @JsonProperty
   private String[] miniatureHeader;
   @JsonProperty
   private String[] miniatureSubtitle;
   @JsonProperty
   private String[] miniatureTitle;

   public CollectionMiniatureDTO() {
   }
}
