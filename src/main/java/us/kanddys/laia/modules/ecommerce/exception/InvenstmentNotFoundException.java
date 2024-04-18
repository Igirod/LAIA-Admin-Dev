package us.kanddys.laia.modules.ecommerce.exception;

/**
 * Esta clase representa una excepción que se lanza cuando no se encuentra una
 * inversión.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public class InvenstmentNotFoundException extends RuntimeException {

   public InvenstmentNotFoundException(String message) {
      super(message);
   }
}
