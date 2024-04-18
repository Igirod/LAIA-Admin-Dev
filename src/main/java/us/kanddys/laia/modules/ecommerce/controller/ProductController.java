package us.kanddys.laia.modules.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import us.kanddys.laia.modules.ecommerce.controller.dto.ArticleDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.ProductDTO;
import us.kanddys.laia.modules.ecommerce.model.Utils.TypeFilter;
import us.kanddys.laia.modules.ecommerce.services.ProductService;

@Controller
public class ProductController {

   @Autowired
   private ProductService productService;

   @QueryMapping("products")
   public List<ProductDTO> getProductsPaginated(@Argument(name = "page") Integer page,
         @Argument(name = "merchantId") Long merchantId,
         @Argument(name = "status") Optional<Integer> status) {
      return productService.getProductsPaginated(page, merchantId, status);
   }

   @MutationMapping("uProduct")
   public Integer updateProduct(@Argument Long productId, @Argument Optional<String> title,
         @Argument Optional<Double> price, @Argument Optional<Integer> stock, @Argument Optional<Integer> status,
         @Argument Optional<String> typeOfSale) {
      return productService.updateProduct(productId, title, price, stock, status, typeOfSale);
   }

   @MutationMapping("dProduct")
   public Integer deleteProduct(@Argument Long productId) {
      return productService.deleteProduct(productId);
   }

   @QueryMapping("gProductsByTypeFilter")
   public List<ProductDTO> getProductsByTypeFilterPaginated(@Argument TypeFilter typeFilter, @Argument Integer page) {
      return productService.getProductsByTypeFilterPaginated(page, typeFilter);
   }

   @MutationMapping("cProduct")
   public Long createProduct(@Argument Optional<Long> userId, @Argument Optional<Long> productId,
         @Argument String title, @Argument String tStock, @Argument Double price, @Argument Integer stock) {
      return productService.createProduct(userId, productId, title, tStock, price, stock);
   }

   @MutationMapping("uAdminSellAssociation")
   public Long updateAdminSellAssociation(@Argument Long productId, @Argument Long userId) {
      return productService.updateAdminSellAssociation(productId, userId);
   }

   @QueryMapping("gAdminSellProduct")
   public ArticleDTO getAdminSellProduct(@Argument Long productId) {
      return productService.getAdminSellProduct(productId);
   }
}
