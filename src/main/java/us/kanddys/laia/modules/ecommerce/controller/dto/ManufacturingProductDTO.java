package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.model.ManufacturingProduct;

/**
 * Esta clase representa un data transfer object (DTO) del coste de fabricación
 * de un producto.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
public class ManufacturingProductDTO {

   @JsonProperty
   private Long id;
   @JsonProperty
   private Long productId;
   @JsonProperty
   private String type;
   @JsonProperty
   private Integer time;

   public ManufacturingProductDTO() {
   }

   /**
    * Constructor personalizado utilizado en diferentes servicios.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param manufacturingProduct
    */
   public ManufacturingProductDTO(ManufacturingProduct manufacturingProduct) {
      super();
      this.id = (manufacturingProduct.getId() != null ? manufacturingProduct.getId() : null);
      this.productId = (manufacturingProduct.getProductId() != null ? manufacturingProduct.getProductId() : null);
      this.type = (manufacturingProduct.getType() != null ? manufacturingProduct.getType() : null);
      this.time = (manufacturingProduct.getTime() != null ? manufacturingProduct.getTime() : null);
   }
}
