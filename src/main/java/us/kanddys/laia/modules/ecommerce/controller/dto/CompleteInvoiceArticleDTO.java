package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.model.InvoiceProduct;

/**
 * Esta clase representa un data transfer object (DTO) de un producto en el
 * carrito de compras.
 * 
 * @author Igirod0
 * @version 1.0.2
 */
@Data
@AllArgsConstructor
public class CompleteInvoiceArticleDTO {
   @JsonProperty
   private Long id;
   @JsonProperty
   private Integer articleCount;
   @JsonProperty
   private String articleTitle;
   @JsonProperty
   private Double articlePrice;
   @JsonProperty
   private String articleUnit;
   @JsonProperty
   private String articleMedia;

   public CompleteInvoiceArticleDTO() {
   }

   /**
    * Constructor personlizado utilizado en diferentes servicios.
    * 
    * @author Igirod0
    * @version 1.0.1
    * @param invoiceProduct
    */
   public CompleteInvoiceArticleDTO(InvoiceProduct invoiceProduct) {
      super();
      this.id = (invoiceProduct.getId().getProductId() == null ? null : invoiceProduct.getId().getProductId());
      this.articleCount = (invoiceProduct.getArticleCount() == null ? null : invoiceProduct.getArticleCount());
      this.articleTitle = (invoiceProduct.getArticleTitle() == null ? null : invoiceProduct.getArticleTitle());
      this.articlePrice = (invoiceProduct.getArticlePrice() == null ? null : invoiceProduct.getArticlePrice());
      this.articleUnit = (invoiceProduct.getArticleUnit() == null ? null : invoiceProduct.getArticleUnit());
      this.articleMedia = (invoiceProduct.getArticleMedia() == null ? null : invoiceProduct.getArticleMedia());
   }
}
