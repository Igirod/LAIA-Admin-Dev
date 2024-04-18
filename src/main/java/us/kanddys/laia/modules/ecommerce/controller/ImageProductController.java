package us.kanddys.laia.modules.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import us.kanddys.laia.modules.ecommerce.controller.dto.ImageProductDTO;
import us.kanddys.laia.modules.ecommerce.services.ImageProductService;

@Controller
public class ImageProductController {
   
   @Autowired
   private ImageProductService imageProductService;

   @QueryMapping("imagesPId")
   public List<ImageProductDTO> getImagesProductByProductId(@Argument Long productId) {
      return imageProductService.getImagesProductByProductId(productId);
   }
}
