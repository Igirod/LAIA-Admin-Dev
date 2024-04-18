package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateCollectionBodyDTO {
   @JsonProperty("libraryId")
   private Long libraryId;
   @JsonProperty("userId")
   private Long userId;
   @JsonProperty("collectionId")
   private Long collectionId;

   public CreateCollectionBodyDTO() {
   }
}
