package us.kanddys.laia.modules.ecommerce.exception;

/**
 * Esta clase representa una excepción que se lanza cuando no se encuentra un
 * comerciante.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public class MerchantNotFoundException extends RuntimeException {
   
   public MerchantNotFoundException(String message) {
      super(message);
   }
}
