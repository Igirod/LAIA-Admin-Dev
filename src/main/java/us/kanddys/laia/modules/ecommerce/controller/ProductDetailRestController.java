package us.kanddys.laia.modules.ecommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import us.kanddys.laia.modules.ecommerce.controller.dto.ProductDetailDTO;
import us.kanddys.laia.modules.ecommerce.services.ProductDetailService;

@RestController
@RequestMapping("/product-detail")
@Tag(name = "Product Detail Rest Controller", description = "Operaciones REST relacionadas al detalle de un producto.")
public class ProductDetailRestController {

   @Autowired
   private ProductDetailService productDetailService;

   @Operation(description = "Servicio que tiene la obligación de crear un detalle asociado a un producto.")
   @Parameters({
         @Parameter(name = "title", description = "Título del detalle", required = true, example = "title"),
         @Parameter(name = "image", description = "Imagen del detalle", required = true, example = "image"),
         @Parameter(name = "productId", description = "Identificador del producto", required = true, example = "1"),
         @Parameter(name = "description", description = "Descripción del detalle", required = true, example = "description")
   })
   @ApiResponse(responseCode = "200", description = "Devuelve el detalle del producto creado.")
   @RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "/upload", produces = {
         "application/json" }, consumes = { "multipart/form-data" })
   public ProductDetailDTO uploadProductDetail(@RequestPart Optional<String> title,
         @RequestPart Optional<MultipartFile> image, @RequestPart String productId,
         @RequestPart Optional<String> description) {
      return productDetailService.createProductDetail(title, image, Long.valueOf(productId), description);
   }
}
