package us.kanddys.laia.modules.ecommerce.controller.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.model.SellerQuestion;

/**
 * Esta clase representa un data transfer object (DTO) de una pregunta
 * relacionada a un comerciante.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
public class SellerQuestionDTO {

   @JsonProperty
   private Long id;
   @JsonProperty
   private String question;
   @JsonProperty
   private String type;
   @JsonProperty
   private Integer required;
   @JsonProperty
   private Integer limit;
   @JsonProperty
   private Long productId;
   @JsonProperty
   private List<String> options;

   public SellerQuestionDTO() {
   }

   public SellerQuestionDTO(SellerQuestion sellerQuestion) {
      super();
      this.id = (sellerQuestion.getId() != null ? sellerQuestion.getId() : null);
      this.limit = (sellerQuestion.getLimit() != null ? sellerQuestion.getLimit() : null);
      this.question = (sellerQuestion.getQuestion() != null ? sellerQuestion.getQuestion() : null);
      this.required = (sellerQuestion.getRequired() != null ? sellerQuestion.getRequired() : null);
      this.type = (sellerQuestion.getType() != null ? sellerQuestion.getType() : null);
      this.productId = (sellerQuestion.getProductId() != null ? sellerQuestion.getProductId() : null);
   }
}
