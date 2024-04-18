package us.kanddys.laia.modules.ecommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import us.kanddys.laia.modules.ecommerce.controller.dto.CombinedProductDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.CombinedProductDetailDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.CombinedShopDTO;
import us.kanddys.laia.modules.ecommerce.model.Utils.Status;
import us.kanddys.laia.modules.ecommerce.services.CombinedService;

@Controller
public class CombinedController {

   @Autowired
   private CombinedService combinedService;

   @QueryMapping("combinedShop")
   public CombinedShopDTO getCombinedShop(@Argument String slug, @Argument Status invoiceStatus,
         @Argument Optional<Long> userId) {
      return combinedService.findCombinedShop(slug, userId);
   }

   @QueryMapping("combinedProduct")
   public CombinedProductDTO getCombinedProduct(@Argument Long productId, @Argument String slug,
         @Argument Optional<Long> userId) {
      return combinedService.findCombinedProduct(productId, slug, userId);
   }

   @QueryMapping("rProduct")
   public CombinedProductDetailDTO getProductById(@Argument Long productId, @Argument Optional<Long> invoiceId,
         @Argument Long merchantId) {
      return combinedService.findCombinedProductDetail(productId, invoiceId, merchantId);
   }
}
