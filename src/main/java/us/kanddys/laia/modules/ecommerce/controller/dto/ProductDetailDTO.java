package us.kanddys.laia.modules.ecommerce.controller.dto;

import java.io.IOException;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.model.ProductDetail;

/**
 * Esta clase representa el detalle de un producto.
 * 
 * @author Igirod0
 * @version 1.0.1
 */
@Data
@AllArgsConstructor
public class ProductDetailDTO {
   @JsonProperty
   private Long id;
   @JsonProperty
   private Long productId;
   @JsonProperty
   private String title;
   @JsonProperty
   private String description;
   @JsonProperty
   private String url;
   @JsonProperty
   private String type;

   public ProductDetailDTO() {
   }

   /**
    * Constructor personalizado se implementa en diferentes servicios.
    *
    * @author Igirod0
    * @version 1.0.1
    * @throws IOException
    */
   public ProductDetailDTO(ProductDetail productDetail) throws IOException {
      this.id = (productDetail.getId() == null) ? null : productDetail.getId();
      this.title = (productDetail.getTitle() != null) ? productDetail.getTitle() : null;
      this.description = (productDetail.getDescription() != null) ? productDetail.getDescription() : null;
      this.productId = (productDetail.getProductId() != null) ? productDetail.getProductId() : null;
      this.url = (productDetail.getUrl() != null) ? productDetail.getUrl() : null;
      this.type = (productDetail.getType() != null) ? productDetail.getType() : null;
   }
}
