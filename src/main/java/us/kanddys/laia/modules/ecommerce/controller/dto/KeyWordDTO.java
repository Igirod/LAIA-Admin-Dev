package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.model.KeyWord;

/**
 * Esta clase representa un data transfer object (DTO) de una palabra clave.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
public class KeyWordDTO {

   @JsonProperty
   private Long id;
   @JsonProperty
   private String word;
   @JsonProperty
   private Long userId;

   public KeyWordDTO() {
   }

   public KeyWordDTO(KeyWord keyWord) {
      super();
      this.id = (keyWord.getId() == null ? null : keyWord.getId());
      this.word = (keyWord.getWord() == null ? null : keyWord.getWord());
      this.userId = (keyWord.getUserId() == null ? null : keyWord.getUserId());
   }
}
