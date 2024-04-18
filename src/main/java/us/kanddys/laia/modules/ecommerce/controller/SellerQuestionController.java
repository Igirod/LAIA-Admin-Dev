package us.kanddys.laia.modules.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import us.kanddys.laia.modules.ecommerce.controller.dto.SellerQuestionDTO;
import us.kanddys.laia.modules.ecommerce.services.SellerQuestionService;

@Controller
public class SellerQuestionController {

   @Autowired
   private SellerQuestionService sellerQuestionService;

   @MutationMapping("uAdminSellQuestion")
   public Integer updateAdminSellQuestions(@Argument Long sellerQuestionId, @Argument Optional<String> value,
         @Argument Optional<String> type, @Argument Optional<Integer> required, @Argument Optional<Integer> limit,
         @Argument Optional<List<String>> options) {
      return sellerQuestionService.updateAdminSellQuestions(sellerQuestionId, value, type, required, limit, options);
   }

   @QueryMapping("gAdminSellQuestions")
   public List<SellerQuestionDTO> getAdminSellQuestions(@Argument Long productId) {
      return sellerQuestionService.getAdminSellQuestions(productId);
   }
}
