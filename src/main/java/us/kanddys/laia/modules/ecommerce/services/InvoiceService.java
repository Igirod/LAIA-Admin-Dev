package us.kanddys.laia.modules.ecommerce.services;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import us.kanddys.laia.modules.ecommerce.controller.dto.CompleteInvoiceDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceAddressInputDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceArticleInputDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoicePaymentDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceReservationDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.utils.LongDTO;

/**
 * Esta interface contiene las obligaciones que debe implementar la
 * clase InvoiceServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.3
 */
public interface InvoiceService {

   /**
    * Este método tiene la obligación de actualizar el estado de la orden.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param id
    * @param status
    * @return
    */
   public Integer updateInvoiceStatus(Long id, String status);

   /**
    * Este método tiene la obligación de actualizar el estado de la orden.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param invoiceId
    * @param status
    * @return Integer
    */
   public Integer updateInvoiceNote(Long invoiceId, String status);

   /**
    * Este método tiene la obligación de buscar una orden por su numero
    * de id.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param orderId
    * @return OrderDTO
    */
   public InvoiceDTO getInvoiceById(Long orderId);

   /**
    * Este método tiene la obligación de actualizar la imagen del voucher de la
    * orden.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param voucher
    * @param invoiceId
    * @return invoicePaymentDTO
    */
   public InvoicePaymentDTO updateInvoiceVoucher(MultipartFile voucher, Long invoiceId);

   /**
    * Este método tiene la obligación de crear una factura asociada a un
    * comerciante.
    * 
    * @param userId
    * @param email
    * @param phone
    * @param name
    * @param lastName
    * @param merchantId
    * @param articles
    * @param address
    * @param batchId
    * @param reservation
    * @param message
    * @return Long
    */
   public LongDTO createAdminSellInvoice(Long userId, String email, String phone,
         String name, String lastName, Long merchantId, List<InvoiceArticleInputDTO> articles,
         InvoiceAddressInputDTO address, Long batchId, String reservation, String reservationType,
         String message);

   /**
    * Este método tiene la obligación de calcular una fecha de envio para una
    * factura, teniendo en cuenta el calendario del comerciante y el tiempo de
    * fabricación de los productos.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param merchantId
    * @param invoiceArticlesIds
    * @return ShippingDateDTO
    */
   public InvoiceReservationDTO gAdminSellInvoiceReservation(Long merchantId, List<Long> invoiceArticlesIds);

   /**
    * Este método tiene la obligación de obtener los datos completos de una
    * factura.
    *
    * @author Igirod0
    * @version 1.0.0
    */
   public CompleteInvoiceDTO gAdminSellInvoice(Long invoiceId, Long userId);
}
