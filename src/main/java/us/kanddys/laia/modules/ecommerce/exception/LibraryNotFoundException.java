package us.kanddys.laia.modules.ecommerce.exception;

/**
 * Esta clase representa una excepción personalizada que se lanza cuando no se
 * encuentra una biblioteca.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public class LibraryNotFoundException extends RuntimeException {

   private static final long serialVersionUID = 1L;

   public LibraryNotFoundException(String message) {
      super(message);
   }
}
