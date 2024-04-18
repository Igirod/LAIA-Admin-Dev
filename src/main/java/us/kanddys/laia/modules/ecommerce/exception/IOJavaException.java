package us.kanddys.laia.modules.ecommerce.exception;

/**
 * Esta clase se lanza cuando se produce un error en la lectura o escritura de un archivo.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public class IOJavaException extends RuntimeException {
   public IOJavaException(String message) {
      super(message);
   }
}
