package us.kanddys.laia.modules.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Entity
@Table(name = "invoices_products")
public class InvoiceProduct {
   @EmbeddedId
   private InvoiceProductId id;
   @Column(name = "article_count")
   private Integer articleCount;
   @Column(name = "article_title")
   private String articleTitle;
   @Column(name = "article_price")
   private Double articlePrice;
   @Column(name = "article_unit")
   private String articleUnit;
   @Column(name = "article_media")
   private String articleMedia;

   public InvoiceProduct() {
   }
}
