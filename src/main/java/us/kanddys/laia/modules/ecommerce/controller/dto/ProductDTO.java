package us.kanddys.laia.modules.ecommerce.controller.dto;

import java.io.IOException;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.model.Product;
import us.kanddys.laia.modules.ecommerce.model.Utils.DateUtils;
import us.kanddys.laia.modules.ecommerce.model.Utils.ProductCheckStock;

/**
 * Esta clase representa un data transfer object (DTO) de un producto.
 * 
 * @author Igirod0
 * @version 1.0.2
 */
@Data
@AllArgsConstructor
public class ProductDTO {
   @JsonProperty
   private Long id;
   @JsonProperty
   private String title;
   @JsonProperty
   private Double price;
   @JsonProperty
   private Integer stock;
   @JsonProperty
   private String frontPage;
   @JsonProperty
   private Long merchantId;
   @JsonProperty
   private Integer status;
   @JsonProperty
   private String createAt;
   @JsonProperty
   private String typeOfSale;
   @JsonProperty
   private String typeOfPrice;
   @JsonProperty
   private Integer manufacturingTime;

   public ProductDTO() {
   }

   /**
    * Constructor personalizado utilizado en diferentes servicios.
    *
    * @author Igirod0
    * @version 1.0.3
    * @throws IOException
    */
   public ProductDTO(Product product) throws IOException {
      super();
      this.id = (product.getId() != null) ? product.getId() : null;
      this.title = (product.getTitle() != null) ? product.getTitle() : null;
      this.price = (product.getPrice() != null) ? product.getPrice() : null;
      this.stock = (product.getStock() != null) ? product.getStock() : null;
      this.frontPage = (product.getFrontPage() != null)
            ? product.getFrontPage()
            : null;
      this.merchantId = (product.getMerchantId() != null) ? product.getMerchantId() : null;
      Optional<Integer> stock = (product.getStock() != null) ? Optional.of(product.getStock()) : Optional.empty();
      this.status = (product.getStatus() != null) ? ProductCheckStock.returnStatusByStock(stock) : null;
      this.createAt = (product.getCreatedAt() != null) ? DateUtils.convertDateToString(product.getCreatedAt()) : null;
      this.typeOfSale = (product.getTypeOfSale() != null) ? product.getTypeOfSale() : null;
   }
}
