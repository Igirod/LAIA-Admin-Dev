package us.kanddys.laia.modules.ecommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import us.kanddys.laia.modules.ecommerce.services.OrderService;

@Controller
public class OrderController {

   @Autowired
   private OrderService orderService;

   @MutationMapping("uOrderN")
   public Integer updateOrderNote(@Argument Long orderId, @Argument String note) {
      return orderService.updateOrderNote(orderId, note);
   }

   @MutationMapping("uOrderS")
   public Integer updateOrderStatus(@Argument Long orderId, @Argument String status) {
      return orderService.updateOrderStatus(orderId, status);
   }

   @MutationMapping("uOrderA")
   public Integer updateOrderAddress(@Argument Long orderId, @Argument Optional<String> address,
         @Argument Optional<Integer> addressNumber, @Argument Optional<String> type, @Argument Optional<String> lat,
         @Argument Optional<String> lng, @Argument Optional<String> direction) {
      return orderService.updateOrderAddress(orderId, direction, lat, lng, addressNumber, type);
   }

   @MutationMapping("uOrderM")
   public Integer updateOrderMessage(@Argument Long orderId, @Argument String message) {
      return orderService.updateOrderMessage(orderId, message);
   }

   @MutationMapping("uOrderR")
   public Integer updateOrderReservation(@Argument Long orderId, @Argument String date,
         @Argument Long batchId) {
      return orderService.updateOrderReservation(orderId, date, batchId);
   }

}
