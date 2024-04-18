package us.kanddys.laia.modules.ecommerce.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase representa una relación entre una categoría y un producto.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
@Entity
@Table(name = "categories_products")
public class CategoryProduct {

   @EmbeddedId
   private CategoryProductId id;
   @ManyToOne
   @JoinColumn(name = "productId", referencedColumnName = "id", insertable = false, updatable = false)
   private Product product;

   public CategoryProduct() {
   }
}
