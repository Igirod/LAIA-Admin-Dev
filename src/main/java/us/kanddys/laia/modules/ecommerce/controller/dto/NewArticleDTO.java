package us.kanddys.laia.modules.ecommerce.controller.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class NewArticleDTO {
   @JsonProperty
   private Long productId;
   @JsonProperty
   private List<ArticleImageDTO> medias;
   @JsonProperty
   private ArticleImageDTO segmentMedia;

   public NewArticleDTO() {
   }
}
