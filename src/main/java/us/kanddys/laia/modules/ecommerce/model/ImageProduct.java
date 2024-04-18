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
 * Esta clase representa una imagen de un producto.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Entity
@Table(name = "images_products")
@Data
@AllArgsConstructor
public class ImageProduct {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;
   @Column(name = "product_id")
   private Long productId;
   @Column(name = "url")
   private String url;
   @Column(name = "type")
   private String type;

   public ImageProduct() {
   }
}
