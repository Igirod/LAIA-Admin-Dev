package us.kanddys.laia.modules.ecommerce.services;

/**
 * Esta interfaz contiene las obligaciones que debe implementar la clase
 * InvoiceCodeServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.1
 */
public interface InvoiceCodeService {

   /**
    * Este método se encarga de generar un código de factura.
    * 
    * @author Igirod0
    * @version 1.0.1
    * @param merchantId
    * @param invoiceId
    * @return InvoiceInputDTO
    */
   public String generateInvoiceCode(Long merchantId, Long invoiceId);
}
