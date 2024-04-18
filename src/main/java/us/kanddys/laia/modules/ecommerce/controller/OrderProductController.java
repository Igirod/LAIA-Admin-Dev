package us.kanddys.laia.modules.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import us.kanddys.laia.modules.ecommerce.controller.dto.OrderProductDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.OrderProductInputDTO;
import us.kanddys.laia.modules.ecommerce.services.OrderProductService;

@Controller
public class OrderProductController {

   @Autowired
   private OrderProductService orderProductService;

   @MutationMapping("aProductO")
   public Integer addInvoiceProduct(@Argument Long orderId, @Argument Long productId) {
      return orderProductService.addOrderProduct(orderId, productId);
   }

   @MutationMapping("uProductO")
   public Integer updateInvoiceProduct(@Argument Long orderId,
         @Argument List<OrderProductInputDTO> listInvoiceProducts) {
      return orderProductService.updateOrderProduct(orderId, listInvoiceProducts);
   }

   @QueryMapping("gOProducts")
   public List<OrderProductDTO> findInvoiceProductsByInvoiceId(@Argument Long orderId, @Argument Integer page,
         @Argument Optional<Integer> limit) {
      return orderProductService.findInvoiceProductsByInvoiceId(orderId, page, limit);
   }
}
