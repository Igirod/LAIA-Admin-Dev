package us.kanddys.laia.modules.ecommerce.exception;

/**
 * Esta clase representa una excepción que se lanza cuando no se encuentra un
 * usuario.
 * 
 * @auhtor Igirod0
 * @version 1.0.0
 */
public class UserNotFoundException extends RuntimeException {

   private static final long serialVersionUID = 1L;

   public UserNotFoundException(String message) {
      super(message);
   }
}
