package us.kanddys.laia.modules.ecommerce.services.payment;

import java.util.List;
import java.util.Optional;

import us.kanddys.laia.modules.ecommerce.controller.dto.PaymentDTO;

/**
 * Esta clase contiene las obligaciones que debe implementar la clase
 * PaymentServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public interface PaymentService {

   /**
    * Este método se encarga de buscar los pagos de forma paginada.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param page
    * @param merchantId
    * @param status
    * @return List<PaymentDTO>
    */
   public List<PaymentDTO> findPaymentsPaginated(Integer page, Optional<Long> merchantId, Optional<Integer> status);

   /**
    * Este método se encarga de crear un pago de PayPal.
    *
    * @return String
    */
   public String createPayPalPayment();
}
