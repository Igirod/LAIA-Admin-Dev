package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.model.ImageProduct;

/**
 * Este data transfer object (DTO) representa una imagen de un producto.
 * 
 * @author Igirod0
 * @version 1.0.1
 */
@Data
@AllArgsConstructor
public class ImageProductDTO {

   @JsonProperty
   private Long id;
   @JsonProperty
   private Long productId;
   @JsonProperty
   private String url;
   @JsonProperty
   private String type;

   public ImageProductDTO() {
   }

   /**
    * Constructor personalizado que se utiliza en diferentes servicios.
    *
    * @author Igirod0
    * @version 1.0.1
    * @param imageProduct
    */
   public ImageProductDTO(ImageProduct imageProduct) {
      this.id = (imageProduct.getId() == null ? null : imageProduct.getId());
      this.productId = (imageProduct.getProductId() == null ? null : imageProduct.getProductId());
      this.url = (imageProduct.getUrl() == null ? null : imageProduct.getUrl());
      this.type = (imageProduct.getType() == null ? null : imageProduct.getType());
   }
}
