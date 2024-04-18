package us.kanddys.laia.modules.ecommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import us.kanddys.laia.modules.ecommerce.services.HashtagProductService;
import us.kanddys.laia.modules.ecommerce.services.HashtagService;

@Controller
public class HashtagController {

   @Autowired
   private HashtagService hashtagService;

   @Autowired
   private HashtagProductService hashtagProductService;

   @MutationMapping("cHashtag")
   public Long createHashtag(@Argument String value) {
      return hashtagService.createHashtag(value);
   }

   @MutationMapping("dHashtag")
   public Integer deleteHashtag(@Argument Long hashtagId) {
      return hashtagService.deleteHashtag(hashtagId);
   }

   @MutationMapping("uHashtag")
   public Integer updateHashtag(@Argument Long hashtagId, @Argument String value) {
      return hashtagService.updateHashtag(hashtagId, value);
   }

   @MutationMapping("cHashtagProduct")
   public Integer createHashtagProduct(@Argument Long hashtagId, @Argument Long productId) {
      return hashtagProductService.createHashtagProduct(hashtagId, productId);
   }

   @MutationMapping("dHashtagProduct")
   public Integer deleteHashtagProduct(@Argument Long hashtagId, @Argument Long productId) {
      return hashtagProductService.deleteHashtagProduct(hashtagId, productId);
   }

   @QueryMapping("wAdminSellHashtag")
   public Integer wAdminSellHashtag(@Argument String hashtag) {
      return hashtagService.wAdminSellHashtag(hashtag);
   }

   @MutationMapping("uAdminSellHashtag")
   public Integer uAdminSellHashtag(@Argument Long productId, @Argument Optional<String> hashtag) {
      return hashtagService.uAdminSellHashtag(productId, hashtag);
   }
}
