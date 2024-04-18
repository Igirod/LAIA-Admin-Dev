package us.kanddys.laia.modules.ecommerce.exception;

/**
 * Esta clase se lanza cuando la palabra clave buscada no existe.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public class KeyWordNotFoundException extends RuntimeException {

   private static final long serialVersionUID = 1L;

   public KeyWordNotFoundException(String message) {
      super(message);
   }
}
