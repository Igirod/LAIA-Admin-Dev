package us.kanddys.laia.modules.ecommerce.exception;

/**
 * Esta clase se lanza cuando el usuario de la biblioteca no es igual al usuario
 * pasado por parámetro.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public class LibraryUserNotEqualException extends RuntimeException {

   private static final long serialVersionUID = 1L;

   public LibraryUserNotEqualException(String message) {
      super(message);
   }
}
