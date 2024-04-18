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
 * Esta clase representa el tiempo de fabricación asociado a un producto.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
@Table(name = "manufacturing_products")
@Entity
public class ManufacturingProduct {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;
   @Column(name = "product_id")
   private Long productId;
   @Column(name = "type")
   private String type;
   @Column(name = "time")
   private Integer time;

   public ManufacturingProduct() {
   }

   public ManufacturingProduct(Long productId, String type, Integer time) {
      this.productId = productId;
      this.type = type;
      this.time = time;
   }
}
