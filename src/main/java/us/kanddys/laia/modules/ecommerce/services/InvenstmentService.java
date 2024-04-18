package us.kanddys.laia.modules.ecommerce.services;

import java.util.List;
import java.util.Optional;

import us.kanddys.laia.modules.ecommerce.controller.dto.InvenstmentDTO;

/**
 * Esta interfaz contiene las obligaciones que debe implementar la clase
 * InventsmentServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public interface InvenstmentService {

   /**
    * Este método crea una inversión asociada a un producto.
    *
    * @author Igirod0
    * @version 1.0.1
    * @param productId
    * @param amount
    * @param note
    * @param title
    * @return Integer
    */
   public Integer createInvenstment(Long productId, Optional<Double> amount, Optional<String> note,
         Optional<String> title);

   /**
    * Este método elimina una inversión asociada a un producto.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param productId
    * @return Integer
    */
   public Integer deleteInvenstment(Long invenstmentId);

   /**
    * Este método actualiza una inversión asociada a un producto.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param amount
    * @param note
    * @param title
    * @return Integer
    */
   public Integer updateInvenstment(Long invenstmentId, Optional<Double> amount, Optional<String> note,
         Optional<String> title);

   /**
    * Este método obtiene las inversiones asociadas a un producto.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param productId
    * @return List<InvenstmentDTO>
    */
   public List<InvenstmentDTO> getAdminSellInvenstments(Long productId);
}
