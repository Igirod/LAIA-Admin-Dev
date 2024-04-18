package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.model.Hashtag;

/**
 * Esta clase representa un data transfer object (DTO) de un hashtag.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
public class HashtagDTO {
   @JsonProperty
   private Long id;
   @JsonProperty
   private String value;

   public HashtagDTO() {
   }

   public HashtagDTO(Hashtag hashtag) {
      super();
      this.id = (hashtag.getId() == null) ? null : hashtag.getId();
      this.value = (hashtag.getValue() == null) ? null : hashtag.getValue();
   }
}
