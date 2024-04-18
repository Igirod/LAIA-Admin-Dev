package us.kanddys.laia.modules.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.model.Utils.TypePayment;

/**
 * Esta clase representa un pago realizado por un usuario.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
@Entity
@Table(name = "payments")
public class Payment {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(name = "merchant_id")
   private Long merchantId;
   @Enumerated(EnumType.STRING)
   private TypePayment type;
   @Column(name = "cvu")
   private String cvu;
   @Column(name = "status")
   private Integer status;
   @Column(name = "title")
   private String title;
   
   public Payment() {}
}
