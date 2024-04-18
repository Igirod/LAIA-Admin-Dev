package us.kanddys.laia.modules.ecommerce.exception;

/**
 * Esta clase contiene la excepción que se lanza cuando se intenta agregar una
 * palabra clave que ya existe.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public class ExistingKeyWordException extends RuntimeException {

   public ExistingKeyWordException(String message) {
      super(message);
   }
}
