package us.kanddys.laia.modules.ecommerce.exception;

/**
 * Esta clase se lanza cuando el tipo de collection no existe.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public class LibraryTypeCollectionNotFoundException extends RuntimeException {

   private static final long serialVersionUID = 1L;

   public LibraryTypeCollectionNotFoundException(String message) {
      super(message);
   }
}
