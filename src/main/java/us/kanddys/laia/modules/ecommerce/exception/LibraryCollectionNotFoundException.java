package us.kanddys.laia.modules.ecommerce.exception;

/**
 * Esta clase representa una excepción que se lanza cuando no se encuentra una
 * colección asociada a una biblioteca.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public class LibraryCollectionNotFoundException extends RuntimeException {

   private static final long serialVersionUID = 1L;

   public LibraryCollectionNotFoundException(String message) {
      super(message);
   }
}
