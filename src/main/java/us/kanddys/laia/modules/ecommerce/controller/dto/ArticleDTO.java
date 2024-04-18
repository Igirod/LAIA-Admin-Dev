package us.kanddys.laia.modules.ecommerce.controller.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase representa un data transfer object (DTO) de un artículo.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
public class ArticleDTO {

   @JsonProperty
   private Long productId;
   @JsonProperty
   private List<String> medias;
   @JsonProperty
   private String title;
   @JsonProperty
   private Double price;
   @JsonProperty
   private String typeOfPrice;
   @JsonProperty
   private Integer stock;
   @JsonProperty
   private Integer invenstmentsCount;
   @JsonProperty
   private HashtagDTO hashtag;
   @JsonProperty
   private Integer segments;
   @JsonProperty
   private ManufacturingProductDTO manufacturingProduct;
   @JsonProperty
   private Integer keywords;
   @JsonProperty
   private Integer questions;

   public ArticleDTO() {
   }
}
