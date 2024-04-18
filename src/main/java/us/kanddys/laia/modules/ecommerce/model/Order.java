package us.kanddys.laia.modules.ecommerce.model;

import java.text.ParseException;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.controller.dto.OrderDTO;
import us.kanddys.laia.modules.ecommerce.model.Utils.DateUtils;
import us.kanddys.laia.modules.ecommerce.model.Utils.Status;

/**
 * Esta clase representa la entidad Order.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;
   @Column(name = "user_id")
   private Long userId;
   @Column(name = "mer_id")
   private Long merchantId;
   @Column(name = "payment_id")
   private Long paymentId;
   @Column(name = "code")
   private String code;
   @Column(name = "reservation")
   private String reservation;
   @Column(name = "total")
   private Double total;
   @Column(name = "message")
   private String message;
   @Enumerated(EnumType.STRING)
   @Column(name = "status")
   private Status status;
   @Column(name = "voucher")
   private String voucher;
   @Column(name = "note")
   private String note;
   @Column(name = "address_lat")
   private String addressLat;
   @Column(name = "address_lng")
   private String addressLng;
   @Column(name = "address_direction")
   private String addressDirection;
   @Transient
   private Integer count;
   @Column(name = "batch_id")
   private Long batchId;
   @Column(name = "address_number")
   private Integer addressNumber;
   @Column(name = "type")
   private String type;
   @Column(name = "create_at")
   private Date createAt;

   public Order() {
   }

   /**
    * Constructor personalizado utilizado en diferentes servicios.
    *
    * @author Igirod0
    * @version 1.0.2
    * @param orderDTO
    * @throws ParseException
    */
   public Order(OrderDTO orderDTO, Integer count) throws ParseException {
      super();
      this.id = (orderDTO.getId() == null) ? null : orderDTO.getId();
      this.merchantId = (orderDTO.getMerchantId() == null) ? null : orderDTO.getMerchantId();
      this.userId = (orderDTO.getUserId() == null) ? null : orderDTO.getUserId();
      this.paymentId = (orderDTO.getPaymentId() == null) ? null : orderDTO.getPaymentId();
      this.code = (orderDTO.getCode() == null) ? null
            : orderDTO.getCode();
      this.reservation = (orderDTO.getReservation() == null) ? null
            : orderDTO.getReservation();
      this.total = (orderDTO.getTotal() == null) ? null : orderDTO.getTotal();
      this.message = (orderDTO.getMessage() == null) ? null : orderDTO.getMessage();
      this.status = (orderDTO.getStatus() == null) ? null : orderDTO.getStatus();
      this.voucher = (orderDTO.getVoucher() == null) ? null : orderDTO.getVoucher();
      this.note = (orderDTO.getNote() == null) ? null : orderDTO.getNote();
      this.count = (count == null) ? null : count;
      this.addressDirection = (orderDTO.getAddressDirection() == null) ? null : orderDTO.getAddressDirection();
      this.addressLat = (orderDTO.getAddressLat() == null) ? null : orderDTO.getAddressLat();
      this.addressLng = (orderDTO.getAddressLng() == null) ? null : orderDTO.getAddressLng();
      this.batchId = (orderDTO.getBatchId() == null) ? null : orderDTO.getBatchId();
      this.addressNumber = (orderDTO.getAddressNumber() == null) ? null : orderDTO.getAddressNumber();
      this.type = (orderDTO.getType() == null) ? null : orderDTO.getType();
      this.createAt = (orderDTO.getCreateAt() == null) ? null : DateUtils.convertStringToDate(orderDTO.getCreateAt());
   }

   /**
    * Constructor personalizado utilizado en diferentes servicios.
    *
    * @author Igirod0
    * @version 1.0.1
    * @param orderDTOId
    * @param merchantId
    * @throws ParseException
    */
   public Order(Long userId, Long merchantId) {
      super();
      this.id = null;
      this.userId = (userId == null) ? null : userId;
      this.merchantId = (merchantId == null) ? null : merchantId;
      this.status = Status.INITIAL;
      this.total = 0.0;
      try {
         this.createAt = DateUtils.getCurrentDateWitheoutTime();
      } catch (ParseException e) {
         throw new RuntimeException("Error al obtener la fecha actual");
      }
   }
}
