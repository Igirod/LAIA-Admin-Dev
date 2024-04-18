package us.kanddys.laia.modules.ecommerce.controller.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.model.ProductDetailShort;

/**
 * Este clase representa un data transfer object (DTO) para el detalle corto de
 * un producto.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
public class ProductDetailShortDTO {
   @JsonProperty
   private Long productId;
   @JsonProperty
   private Integer stock;
   @JsonProperty
   private List<ImageProductDTO> images;

   public ProductDetailShortDTO() {
   };

   /**
    * Constructor personalizado utilizado en diferentes servicios.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param productDetailShort
    */
   public ProductDetailShortDTO(ProductDetailShort productDetailShort) {
      this.productId = (productDetailShort.getProductId() == null) ? null : productDetailShort.getProductId();
      this.stock = (productDetailShort.getStock() == null) ? null : productDetailShort.getStock();
      this.images = (productDetailShort.getImages() == null) ? null
            : productDetailShort.getImages().stream().map(ImageProductDTO::new).toList();
   }
}
