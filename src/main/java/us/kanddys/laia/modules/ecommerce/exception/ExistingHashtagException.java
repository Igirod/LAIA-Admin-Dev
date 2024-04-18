package us.kanddys.laia.modules.ecommerce.exception;

/**
 * Esta clase representa la excepcion que se lanza cuando se intenta crear un
 * hashtag que ya existe.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public class ExistingHashtagException extends RuntimeException {

   private static final long serialVersionUID = 1L;

   public ExistingHashtagException(String message) {
      super(message);
   }
}
