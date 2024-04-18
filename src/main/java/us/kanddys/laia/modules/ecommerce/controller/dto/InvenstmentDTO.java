package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.model.Invenstment;

/**
 * Esta clase representa un data transfer object (DTO) de una inversión.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
public class InvenstmentDTO {
   @JsonProperty
   private Long id;
   @JsonProperty
   private Long productId;
   @JsonProperty
   private Double amount;
   @JsonProperty
   private String note;

   public InvenstmentDTO() {
   }

   /**
    * Constructor personalizado utilizado en diferentes servicios.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param invenstment
    */
   public InvenstmentDTO(Invenstment invenstment) {
      super();
      this.id = (invenstment.getId() != null ? invenstment.getId() : null);
      this.productId = (invenstment.getProductId() != null ? invenstment.getProductId() : null);
      this.amount = (invenstment.getAmount() != null ? invenstment.getAmount() : null);
      this.note = (invenstment.getNote() != null ? invenstment.getNote() : null);
   }
}
