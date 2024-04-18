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
 * Esta clase representa la entidad AuxiliarProductMedia.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
@Entity
@Table(name = "aux_products_medias")
public class AuxiliarProductMedia {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "aux_product_medias")
   private Long id;
   @Column(name = "aux_product_id")
   private Long auxProductId;
   @Column(name = "url")
   private String url;
   @Column(name = "type")
   private String type;

   public AuxiliarProductMedia() {
   }
}
