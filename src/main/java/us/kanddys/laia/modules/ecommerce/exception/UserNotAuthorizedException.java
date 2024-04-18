package us.kanddys.laia.modules.ecommerce.exception;

/**
 * Esta clase se lanza cuando el usuario no esta autorizado para realizar una
 * accion.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public class UserNotAuthorizedException extends RuntimeException {

   private static final long serialVersionUID = 1L;

   public UserNotAuthorizedException(String message) {
      super(message);
   }
}
