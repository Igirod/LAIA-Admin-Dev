package us.kanddys.laia.modules.ecommerce.exception;

/**
 * Esta clase representa una excepción que se lanza cuando no se encuentra una factura.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public class InvoiceNotFoundException extends RuntimeException{
   public InvoiceNotFoundException(String message) {
      super(message);
   }
}
