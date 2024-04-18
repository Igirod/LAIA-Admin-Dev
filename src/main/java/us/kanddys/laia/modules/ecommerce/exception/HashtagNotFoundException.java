package us.kanddys.laia.modules.ecommerce.exception;

/**
 * Esta clase representa una excepción que se lanza cuando no se encuentra un
 * hashtag.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public class HashtagNotFoundException extends RuntimeException {

   public HashtagNotFoundException(String message) {
      super(message);
   }
}
