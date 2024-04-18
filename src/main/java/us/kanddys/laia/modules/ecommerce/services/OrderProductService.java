package us.kanddys.laia.modules.ecommerce.services;

import java.util.List;
import java.util.Optional;
import us.kanddys.laia.modules.ecommerce.controller.dto.OrderProductDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.OrderProductInputDTO;

/**
 * Esta interface contiene las obligaciones que debe implementar la clase
 * ShoppingCartServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.1
 */
public interface OrderProductService {

   /**
    * Este metodo se encarga de agregar un producto a una factura.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param orderId
    * @param productId
    * @return Integer
    */
   public Integer addOrderProduct(Long orderId, Long productId);

   /**
    * Este metodo se encarga de actualizar la cantidad de un producto en una
    * factura.
    *
    * @author Igirod0
    * @version 1.0.1
    * @param orderId
    * @param listInvoiceProducts
    * @return Integer
    */
   public Integer updateOrderProduct(Long orderId, List<OrderProductInputDTO> listInvoiceProducts);

   /**
    * Este método tiene la obligación de obtener todos los productos asociados
    * a una factura.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param orderId
    * @param page
    * @param limit
    * @return List<OrderProductDTO>
    */
   public List<OrderProductDTO> findInvoiceProductsByInvoiceId(Long orderId, Integer page, Optional<Integer> limit);
}
