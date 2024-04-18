package us.kanddys.laia.modules.ecommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import us.kanddys.laia.modules.ecommerce.controller.dto.BuyerDTO;
import us.kanddys.laia.modules.ecommerce.services.BuyerService;

@Controller
public class BuyerController {

   @Autowired
   private BuyerService buyerService;

   @QueryMapping("gBuyer")
   public BuyerDTO gBuyer(@Argument Long buyerId) {
      return buyerService.getBuyerById(buyerId);
   }

   @MutationMapping("cBuyer")
   public Long cBuyer(@Argument Long merchantId, @Argument String email, @Argument String name,
         @Argument String surname, @Argument String phone, @Argument Integer count, @Argument String media) {
      return buyerService.cBuyer(merchantId, email, name, surname, phone, count, media);
   }

   @MutationMapping("uBuyer")
   public Integer uBuyer(@Argument Long buyerId, @Argument Optional<String> email, @Argument Optional<String> name,
         @Argument Optional<String> surname, @Argument Optional<String> phone, @Argument Optional<Integer> count,
         @Argument Optional<String> media) {
      return buyerService.uBuyer(buyerId, email, name, surname, phone, count, media);
   }

   @MutationMapping("dBuyer")
   public Integer dBuyer(@Argument Long buyerId) {
      return buyerService.dBuyer(buyerId);
   }
}
