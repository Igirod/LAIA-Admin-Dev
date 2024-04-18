package us.kanddys.laia.modules.ecommerce.controller;

import java.util.List;
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
import us.kanddys.laia.modules.ecommerce.controller.dto.ImageProductDTO;
import us.kanddys.laia.modules.ecommerce.services.ImageProductService;

@RestController
@Tag(name = "Image Product Rest Controller", description = "Operaciones REST relacionadas a las imagenes de los productos.")
public class ImageProductRestController {

   @Autowired
   private ImageProductService imageProductService;

   @Operation(description = "Servicio que tiene la obligación de subir una imagen asociada a un producto.")
   @Parameters({
         @Parameter(name = "image", description = "Imagen del producto", required = true, example = "image"),
         @Parameter(name = "productId", description = "Identificador del producto", required = true, example = "1") })
   @ApiResponse(responseCode = "200", description = "Devuelve el data transfer object de ImageProductDTO.")
   @RequestMapping(method = { RequestMethod.POST }, value = "/upload", produces = {
         "application/json" }, consumes = { "multipart/form-data" })
   public ImageProductDTO uploadImageProduct(@RequestPart MultipartFile image, @RequestPart String productId) {
      return imageProductService.uploadImageProduct(image, Long.valueOf(productId));
   }

   @RequestMapping(method = { RequestMethod.POST }, value = "/product-medias", produces = {
         "application/json" }, consumes = { "multipart/form-data" })
   public Long uploadImagesProducts(@RequestPart List<MultipartFile> medias,
         @RequestPart Optional<String> productId) {
      return imageProductService.uploadImagesProducts(medias, productId);
   }
}
