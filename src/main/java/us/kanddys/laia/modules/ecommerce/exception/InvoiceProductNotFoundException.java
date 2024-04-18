package us.kanddys.laia.modules.ecommerce.exception;

/**
 * Esta clase representa una excepción que se lanza cuando no se encuentra un
 * carrito de compras.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public class InvoiceProductNotFoundException extends RuntimeException {
   public InvoiceProductNotFoundException(String message) {
      super(message);
   }
}
