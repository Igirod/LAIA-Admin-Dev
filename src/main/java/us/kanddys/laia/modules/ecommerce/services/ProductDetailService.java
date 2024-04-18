package us.kanddys.laia.modules.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import us.kanddys.laia.modules.ecommerce.controller.dto.ProductDetailDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.ProductDetailShortDTO;

/**
 * Esta clase contiene las obligaciones que debe implementar
 * productDetailServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public interface ProductDetailService {

   /**
    * Este método retorna los detalles de un producto.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param productId
    * @return List<ProductDetailDTO>
    */
   List<ProductDetailDTO> getProductDetailsByProductId(Long productId);

   /**
    * Este método crea un nuevo detalle de producto.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param description
    * @param frontPage
    * @param productId
    * @param title
    * @return ProductDetailDTO
    */
   public ProductDetailDTO createProductDetail(Optional<String> title, Optional<MultipartFile> frontPage,
         Long productId,
         Optional<String> description);

   /**
    * Este método crea un nuevo detalle de producto.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param description
    * @param frontPage
    * @param productId
    * @param title
    * @return ProductDetailDTO
    */
   public ProductDetailDTO createProductDetailFrontPageString(Optional<String> title, Optional<String> frontPage,
         Long productId,
         Optional<String> description);

   /**
    * Este método se encarga de obtener el detalle acortado de un producto.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param productId
    * @return ProductDetailShortDTO
    */
   public ProductDetailShortDTO getProductDetailShort(Long productId);
}
