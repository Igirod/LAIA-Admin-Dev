package us.kanddys.laia.modules.ecommerce.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase representa una reserva.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
@Entity
@Table(name = "reservations")
public class Reservation {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;
   @Column(name = "mer_id")
   private Long merchantId;
   @Column(name = "user_id")
   private Long userId;
   @Column(name = "batch_id")
   private Long batchId;
   @Column(name = "date")
   private Date date;
   @Column(name = "type")
   private String type;

   public Reservation() {
   }
}
