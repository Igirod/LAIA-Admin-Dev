package us.kanddys.laia.modules.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "products_details")
@AllArgsConstructor
@Data
public class ProductDetail {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;
   @Column(name = "product_id")
   private Long productId;
   @Column(name = "title")
   private String title;
   @Column(name = "description")
   private String description;
   @Column(name = "url")
   private String url;
   @Column(name = "type")
   private String type;

   public ProductDetail() {
   }
}
