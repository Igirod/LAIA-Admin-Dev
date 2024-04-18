package us.kanddys.laia.modules.ecommerce.controller.dto;

import java.io.IOException;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.model.Order;
import us.kanddys.laia.modules.ecommerce.model.Utils.DateUtils;
import us.kanddys.laia.modules.ecommerce.model.Utils.Status;

/**
 * Esta clase representa un data transfer object (DTO) para la entidad Order.
 * 
 * @author Igirod0
 * @version 1.0.1
 */
@Data
@AllArgsConstructor
public class OrderDTO {
   @JsonProperty
   private Long id;
   @JsonProperty
   private Long merchantId;
   @JsonProperty
   private Long userId;
   @JsonProperty
   private Long paymentId;
   @JsonProperty
   private String code;
   @JsonProperty
   private String reservation;
   @JsonProperty
   private Double total;
   @JsonProperty
   private String message;
   @JsonProperty
   private Status status;
   @JsonProperty
   private String voucher;
   @JsonProperty
   private String note;
   @JsonProperty
   private Integer count;
   @JsonProperty
   private String addressLat;
   @JsonProperty
   private String addressLng;
   @JsonProperty
   private String addressDirection;
   @JsonProperty
   private Long batchId;
   @JsonProperty
   private Integer addressNumber;
   @JsonProperty
   private String type;
   @JsonProperty
   private String merchantDirection;
   @JsonProperty
   private String merchantLat;
   @JsonProperty
   private String merchantLng;
   @JsonProperty
   private String createAt;

   public OrderDTO() {
   }

   /**
    * Constructor personalizado utilizado en diferentes servicios.
    *
    * @author Igirod0
    * @version 1.0.1
    * @param order
    * @throws IOException
    */
   public OrderDTO(Order order) throws IOException {
      super();
      this.id = (order.getId() == null) ? null : order.getId();
      this.merchantId = (order.getMerchantId() == null) ? null : order.getMerchantId();
      this.userId = (order.getUserId() == null) ? null : order.getUserId();
      this.paymentId = (order.getPaymentId() == null) ? null : order.getPaymentId();
      this.code = (order.getCode() == null) ? null : order.getCode();
      this.reservation = (order.getReservation() == null) ? null
            : order.getReservation();
      this.total = (order.getTotal() == null) ? null : order.getTotal();
      this.message = (order.getMessage() == null) ? null : order.getMessage();
      this.status = (order.getStatus().toString() == null) ? null : order.getStatus();
      this.voucher = (order.getVoucher() == null) ? null : order.getVoucher();
      this.note = (order.getNote() == null) ? null : order.getNote();
      this.count = (order.getCount() == null) ? null : order.getCount();
      this.addressDirection = (order.getAddressDirection() == null) ? null : order.getAddressDirection();
      this.addressLat = (order.getAddressLat() == null) ? null : order.getAddressLat();
      this.addressLng = (order.getAddressLng() == null) ? null : order.getAddressLng();
      this.batchId = (order.getBatchId() == null) ? null : order.getBatchId();
      this.type = (order.getType() == null) ? null : order.getType();
      this.addressNumber = (order.getAddressNumber() == null) ? null : order.getAddressNumber();
      this.createAt = (order.getCreateAt() == null) ? null : DateUtils.convertDateToString(order.getCreateAt());
   }
}
