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
 * Esta clase contiene los atributos y métodos necesarios para representar un
 * compra.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
@Table(name = "report_shoppings")
@Entity
public class ReportShopping {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;
   @Column(name = "buyer_id")
   private Long buyerId;
   @Column(name = "merchant_id")
   private Long merchantId;
   @Column(name = "month")
   private Integer month;
   @Column(name = "year")
   private Integer year;
   @Column(name = "total")
   private Double total;
   @Column(name = "count")
   private Integer count;

   public ReportShopping() {
   }
}
