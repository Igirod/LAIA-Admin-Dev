package us.kanddys.laia.modules.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import us.kanddys.laia.modules.ecommerce.controller.dto.ProductDetailDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.ProductDetailShortDTO;
import us.kanddys.laia.modules.ecommerce.services.ProductDetailService;

@Controller
public class ProductDetailController {

   @Autowired
   private ProductDetailService productDetailService;

   @QueryMapping("gAdminSellSegments")
   public List<ProductDetailDTO> getProductDetails(@Argument(name = "productId") Long productId) {
      return productDetailService.getProductDetailsByProductId(productId);
   }

   @QueryMapping("productDSId")
   public ProductDetailShortDTO getProductDetailsShort(@Argument(name = "productId") Long productId) {
      return productDetailService.getProductDetailShort(productId);
   }
}
