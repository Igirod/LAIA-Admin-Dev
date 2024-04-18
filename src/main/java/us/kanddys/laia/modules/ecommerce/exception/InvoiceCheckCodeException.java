package us.kanddys.laia.modules.ecommerce.exception;

/**
 * Esta clase se lanza cuando se detecta que la factura no pasa el
 * servicio de validación de sus datos.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public class InvoiceCheckCodeException extends RuntimeException {
   public InvoiceCheckCodeException(String message) {
      super(message);
   }
}
