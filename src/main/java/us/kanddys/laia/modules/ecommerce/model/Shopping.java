package us.kanddys.laia.modules.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase contiene los métodos y atributos necesarios para representar una
 * venta.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
@Table(name = "shoppings")
@Entity
public class Shopping {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;
   @Column(name = "buyer_id")
   private Long buyerId;
   @Column(name = "merchant_id")
   private Long merchantId;
   @Column(name = "day")
   private Integer day;
   @Column(name = "month")
   private Integer month;
   @Column(name = "year")
   private Integer year;
   @Column(name = "count")
   private Integer count;
   @Column(name = "total")
   private Double total;

   public Shopping() {
   }
}
