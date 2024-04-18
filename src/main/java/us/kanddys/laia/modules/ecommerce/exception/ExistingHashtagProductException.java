package us.kanddys.laia.modules.ecommerce.exception;

/**
 * Esta clase representa una excepción que se lanza cuando se intenta crear un
 * hashtag que ya existe.
 * 
 * @auhtor Igirod0
 * @version 1.0.0
 */
public class ExistingHashtagProductException extends RuntimeException {

   private static final long serialVersionUID = 1L;

   public ExistingHashtagProductException(String message) {
      super(message);
   }
}
