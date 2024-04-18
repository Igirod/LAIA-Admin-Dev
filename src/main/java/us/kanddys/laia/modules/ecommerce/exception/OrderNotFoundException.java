package us.kanddys.laia.modules.ecommerce.exception;

/**
 * Esta clase representa la excepción OrderNotFoundException.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public class OrderNotFoundException extends RuntimeException {
   public OrderNotFoundException(String message) {
      super(message);
   }
}
