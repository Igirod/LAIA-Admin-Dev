package us.kanddys.laia.modules.ecommerce.services;

import java.util.Optional;
import us.kanddys.laia.modules.ecommerce.controller.dto.CombinedProductDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.CombinedProductDetailDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.CombinedShopDTO;

/**
 * Esta interface contiene las obligaciones que debe implementar la clase
 * ShopServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.2
 */
public interface CombinedService {

   /**
    * Este metodo tiene la obligación de buscar una tienda y sus productos. Crea
    * una factura si el usuario esta presente.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param slug
    * @param userId
    * @return CombinedShopDTO
    */
   public CombinedShopDTO findCombinedShop(String slug, Optional<Long> userId);

   /**
    * Este metodo tiene la obligación de buscar un producto y sus imagenes. Crea
    * una factura si el usuario esta presente.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param productId
    * @param slug
    * @param userId
    * @return CombinedProductDTO
    */
   public CombinedProductDTO findCombinedProduct(Long productId, String slug, Optional<Long> userId);

   /**
    * Este método tiene la obligación de buscar un producto, sus imagenes y los
    * detalles
    * relacionados por el id.
    *
    * @author Igirod0
    * @version 1.0.2
    * @param productId
    * @param invoiceId
    * @return CombinedProductDetailDTO
    */
   public CombinedProductDetailDTO findCombinedProductDetail(Long productId, Optional<Long> invoiceId, Long merchantId);
}
