package us.kanddys.laia.modules.ecommerce.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase representa el detalle corto de un producto.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Table(name = "products")
@Entity
@Data
@AllArgsConstructor
public class ProductDetailShort {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long productId;
   @Column(name = "stock")
   private Integer stock;
   @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
   @JoinColumn(name = "product_id", referencedColumnName = "id")
   private List<ImageProduct> images;

   public ProductDetailShort() {
   }
}
