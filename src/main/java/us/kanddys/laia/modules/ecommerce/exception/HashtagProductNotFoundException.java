package us.kanddys.laia.modules.ecommerce.exception;

/**
 * Esta clase se encarga de manejar las excepciones de los hashtag de los
 * productos.
 * 
 * @auhtor Igirod0
 * @version 1.0.0
 */
public class HashtagProductNotFoundException extends RuntimeException {

   public HashtagProductNotFoundException(String message) {
      super(message);
   }
}
