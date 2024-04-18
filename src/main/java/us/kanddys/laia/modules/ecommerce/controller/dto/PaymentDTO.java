package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.model.Payment;
import us.kanddys.laia.modules.ecommerce.model.Utils.TypePayment;

@Data
@AllArgsConstructor
public class PaymentDTO {
   @JsonProperty
   private Long id;
   @JsonProperty
   private Long merchantId;
   @JsonProperty
   private Integer status;
   @JsonProperty
   private TypePayment typePayment;
   @JsonProperty
   private String title;
   @JsonProperty
   private String cvu;

   public PaymentDTO() {
   }

   /**
    * Constructor personalizado utilizado en diferentes servicios.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param payment
    */
   public PaymentDTO(Payment payment) {
      super();
      this.id = (payment.getId() == null ? 0 : payment.getId());
      this.typePayment = (payment.getType() == null ? null : payment.getType());
      this.title = (payment.getTitle() == null ? null : payment.getTitle());
      this.cvu = (payment.getCvu() == null ? null : payment.getCvu());
      this.merchantId = (payment.getMerchantId() == null ? 0 : payment.getMerchantId());
      this.status = (payment.getStatus() == null ? 0 : payment.getStatus());
   }
}
