package us.kanddys.laia.modules.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
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
import jakarta.transaction.Transactional;
import us.kanddys.laia.modules.ecommerce.controller.dto.ArticleImageDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.NewArticleDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.ProductDTO;
import us.kanddys.laia.modules.ecommerce.services.AuxiliarProductService;
import us.kanddys.laia.modules.ecommerce.services.ProductService;

@RestController
@RequestMapping("/product")
@Tag(name = "Product Rest Controller", description = "Operaciones REST relacionadas a los productos.")
public class ProductRestController {

   @Autowired
   private ProductService productService;

   @Autowired
   private AuxiliarProductService auxiliarProductService;

   @Operation(description = "Servicio que tiene la obligación de subir la imagen de la portada de un producto.")
   @Parameters({
         @Parameter(name = "image", description = "Imagen de la portada", required = true, example = "image"),
         @Parameter(name = "productId", description = "Identificador del producto", required = true, example = "1") })
   @ApiResponse(responseCode = "1", description = "Devuelve 1 si se actualizó correctamente la imagen de portada.")
   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @RequestMapping(method = { RequestMethod.POST }, value = "/upload", produces = {
         "application/json" }, consumes = { "multipart/form-data" })
   public Integer uploadFrontPage(@RequestPart MultipartFile image, @RequestPart String productId) {
      productService.updateFrontPage(Long.valueOf(productId), image);
      return 1;
   }

   @Operation(description = "Servicio que tiene la obligación de crear un producto.")
   @Parameters({
         @Parameter(name = "frontPage", description = "Imagen de la portada", required = true, example = "image"),
         @Parameter(name = "userId", description = "Identificador del comerciante", required = true, example = "1"),
         @Parameter(name = "title", description = "Título del producto", required = true, example = "Producto 1"),
         @Parameter(name = "productId", description = "Identificador del producto", required = true, example = "1"),
         @Parameter(name = "typeOfSale", description = "Tipo de venta", required = true, example = "Venta"),
         @Parameter(name = "price", description = "Precio del producto", required = true, example = "100.0"),
         @Parameter(name = "stock", description = "Stock del producto", required = true, example = "10"),
         @Parameter(name = "status", description = "Estado del producto", required = true, example = "1"),
         @Parameter(name = "manufacturingTime", description = "Tiempo de fabricación", required = true, example = "10"),
         @Parameter(name = "invenstmentNote", description = "Nota de la inversión", required = true, example = "Nota"),
         @Parameter(name = "invenstmentAmount", description = "Monto de la inversión", required = true, example = "100.0"),
         @Parameter(name = "invenstmentTitle", description = "Título de la inversión", required = true, example = "Inversión"),
         @Parameter(name = "manufacturingType", description = "Tipo de fabricación", required = true, example = "Tipo"),
         @Parameter(name = "segmentTitle", description = "Título del segmento", required = true, example = "Título"),
         @Parameter(name = "segmentDescription", description = "Descripción del segmento", required = true, example = "Descripción"),
         @Parameter(name = "segmentMedia", description = "Medio del segmento", required = true, example = "Medio"),
         @Parameter(name = "hashtagValue", description = "Valor del hashtag", required = true, example = "Hashtag"),
         @Parameter(name = "keywordValue", description = "Valor de la palabra clave", required = true, example = "Palabra clave"),
         @Parameter(name = "sellerQuestionValue", description = "Valor de la pregunta del vendedor", required = true, example = "Pregunta"),
         @Parameter(name = "sellerQuestionType", description = "Tipo de pregunta del vendedor", required = true, example = "MULTIPLE"),
         @Parameter(name = "sellerQuestionLimit", description = "Límite de la pregunta del vendedor", required = true, example = "10"),
         @Parameter(name = "sellerQuestionRequired", description = "Requerido de la pregunta del vendedor", required = true, example = "1"),
         @Parameter(name = "typeOfPrice", description = "Tipo de precio", required = true, example = "USD"),
         @Parameter(name = "sellerQuestionOptions", description = "Opciones de la pregunta del vendedor", required = true, example = "Opción 1, Opción 2") })
   @ApiResponse(responseCode = "1", description = "Devuelve el id del nuevo producto si se creó correctamente.")
   @RequestMapping(method = { RequestMethod.POST }, value = "/create", produces = {
         "application/json" }, consumes = { "multipart/form-data" })
   public ProductDTO createProduct(@RequestPart Optional<MultipartFile> frontPage, @RequestPart Optional<String> userId,
         @RequestPart Optional<String> title, @RequestPart Optional<String> typeOfSale,
         @RequestPart Optional<String> price, @RequestPart Optional<String> stock, @RequestPart Optional<String> status,
         @RequestPart Optional<String> manufacturingTime, @RequestPart Optional<String> invenstmentNote,
         @RequestPart Optional<String> invenstmentAmount, @RequestPart Optional<String> invenstmentTitle,
         @RequestPart Optional<String> manufacturingType, @RequestPart Optional<String> segmentTitle,
         @RequestPart Optional<String> segmentDescription, @RequestPart Optional<MultipartFile> segmentMedia,
         @RequestPart Optional<String> hashtagValue, @RequestPart Optional<List<String>> keywordValue,
         @RequestPart Optional<String> sellerQuestionValue, @RequestPart Optional<String> sellerQuestionType,
         @RequestPart Optional<String> sellerQuestionLimit, @RequestPart Optional<String> sellerQuestionRequired,
         @RequestPart Optional<String> typeOfPrice,
         @RequestPart Optional<List<String>> sellerQuestionOptions) {
      return productService.createProduct(frontPage, title, typeOfSale, price, stock, status,
            userId, manufacturingTime, invenstmentNote, invenstmentAmount, invenstmentTitle, manufacturingType,
            segmentTitle, segmentDescription, segmentMedia, hashtagValue, keywordValue, sellerQuestionValue,
            sellerQuestionType, sellerQuestionLimit, sellerQuestionRequired, typeOfPrice,
            sellerQuestionOptions);
   }

   @Operation(description = "Servicio que tiene la obligación de crear un producto.")
   @Parameters({
         @Parameter(name = "frontPage", description = "Imagen de la portada", required = true, example = "image"),
         @Parameter(name = "userId", description = "Identificador del comerciante", required = true, example = "1"),
         @Parameter(name = "title", description = "Título del producto", required = true, example = "Producto 1"),
         @Parameter(name = "productId", description = "Identificador del producto", required = true, example = "1"),
         @Parameter(name = "typeOfSale", description = "Tipo de venta", required = true, example = "Venta"),
         @Parameter(name = "price", description = "Precio del producto", required = true, example = "100.0"),
         @Parameter(name = "stock", description = "Stock del producto", required = true, example = "10"),
         @Parameter(name = "status", description = "Estado del producto", required = true, example = "1"),
         @Parameter(name = "manufacturingTime", description = "Tiempo de fabricación", required = true, example = "10"),
         @Parameter(name = "invenstmentNote", description = "Nota de la inversión", required = true, example = "Nota"),
         @Parameter(name = "invenstmentAmount", description = "Monto de la inversión", required = true, example = "100.0"),
         @Parameter(name = "invenstmentTitle", description = "Título de la inversión", required = true, example = "Inversión"),
         @Parameter(name = "manufacturingType", description = "Tipo de fabricación", required = true, example = "Tipo"),
         @Parameter(name = "segmentTitle", description = "Título del segmento", required = true, example = "Título"),
         @Parameter(name = "segmentDescription", description = "Descripción del segmento", required = true, example = "Descripción"),
         @Parameter(name = "segmentMedia", description = "Medio del segmento", required = true, example = "Medio"),
         @Parameter(name = "hashtagValue", description = "Valor del hashtag", required = true, example = "Hashtag"),
         @Parameter(name = "keywordValue", description = "Valor de la palabra clave", required = true, example = "Palabra clave"),
         @Parameter(name = "sellerQuestionValue", description = "Valor de la pregunta del vendedor", required = true, example = "Pregunta"),
         @Parameter(name = "sellerQuestionType", description = "Tipo de pregunta del vendedor", required = true, example = "MULTIPLE"),
         @Parameter(name = "sellerQuestionLimit", description = "Límite de la pregunta del vendedor", required = true, example = "10"),
         @Parameter(name = "sellerQuestionRequired", description = "Requerido de la pregunta del vendedor", required = true, example = "1"),
         @Parameter(name = "typeOfPrice", description = "Tipo de precio", required = true, example = "USD") })
   @ApiResponse(responseCode = "1", description = "Devuelve el id del producto auxiliar si se creó correctamente.")
   @RequestMapping(method = { RequestMethod.POST }, value = "/create-aux", produces = {
         "application/json" }, consumes = { "multipart/form-data" })
   public NewArticleDTO createAuxiliarProduct(@RequestPart Optional<List<MultipartFile>> medias,
         @RequestPart Optional<String> userId,
         @RequestPart Optional<String> title, @RequestPart Optional<String> typeOfSale,
         @RequestPart Optional<String> price, @RequestPart Optional<String> stock, @RequestPart Optional<String> status,
         @RequestPart Optional<String> manufacturingTime, @RequestPart Optional<String> invenstmentNote,
         @RequestPart Optional<String> invenstmentAmount, @RequestPart Optional<String> invenstmentTitle,
         @RequestPart Optional<String> manufacturingType, @RequestPart Optional<String> segmentTitle,
         @RequestPart Optional<String> segmentDescription, @RequestPart Optional<MultipartFile> segmentMedia,
         @RequestPart Optional<String> hashtagValue, @RequestPart Optional<String> keywords,
         @RequestPart Optional<String> sellerQuestionValue, @RequestPart Optional<String> sellerQuestionType,
         @RequestPart Optional<String> sellerQuestionLimit, @RequestPart Optional<String> sellerQuestionRequired,
         @RequestPart Optional<String> typeOfPrice,
         @RequestPart Optional<String> sellerQuestionOptions) {
      return auxiliarProductService.createAuxiliarProduct(medias, title, typeOfSale, price, stock, status,
            userId, manufacturingTime, invenstmentNote, invenstmentAmount, invenstmentTitle, manufacturingType,
            segmentTitle, segmentDescription, segmentMedia, hashtagValue,
            (keywords.isPresent()) ? Optional.of(List.of(keywords.get().split("♀")))
                  : Optional.empty(),
            sellerQuestionValue, sellerQuestionType, sellerQuestionLimit, sellerQuestionRequired, typeOfPrice,
            (sellerQuestionOptions.isPresent()) ? Optional.of(List.of(sellerQuestionOptions.get().split("♀")))
                  : Optional.empty());
   }

   @Operation(description = "Servicio que tiene la obligación de actualizar las medias asociadas a un producto.")
   @Parameters({
         @Parameter(name = "productId", description = "Identificador del producto", required = true, example = "1"),
         @Parameter(name = "index", description = "Índice de la media", required = true, example = "N | E"),
         @Parameter(name = "title", description = "Título de la media", required = true, example = "Título de producto"),
         @Parameter(name = "price", description = "Precio del producto", required = true, example = "100.0"),
         @Parameter(name = "tPrice", description = "Precio de la media", required = true, example = "100.0"),
         @Parameter(name = "stock", description = "Stock de la media", required = true, example = "10"),
         @Parameter(name = "tStock", description = "Stock de la media", required = true, example = "10"),
         @Parameter(name = "existImages", description = "Imágenes existentes", required = true),
         @Parameter(name = "newImages", description = "Nuevas imágenes", required = true) })
   @RequestMapping(method = { RequestMethod.PUT }, value = "/update-medias", produces = {
         "application/json" }, consumes = { "multipart/form-data" })
   public List<ArticleImageDTO> updateAdminSellProductMedia(@Argument String productId,
         @RequestPart String index, @RequestPart Optional<String> title, @RequestPart Optional<String> price,
         @RequestPart Optional<String> tPrice, @RequestPart Optional<String> stock,
         @RequestPart Optional<String> tStock, @RequestPart List<MultipartFile> newImages,
         @RequestPart List<String> existImages) {
      return productService.updateAdminSellProductMedia(Long.valueOf(productId), index, title, price, tPrice, stock,
            tStock, existImages, newImages);
   }
}
