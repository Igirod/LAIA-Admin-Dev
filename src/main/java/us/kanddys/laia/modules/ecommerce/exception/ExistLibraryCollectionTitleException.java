package us.kanddys.laia.modules.ecommerce.exception;

/**
 * Esta clase se lanza cuando ya existe una colección con el mismo título.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public class ExistLibraryCollectionTitleException extends RuntimeException {

   private static final long serialVersionUID = 1L;

   public ExistLibraryCollectionTitleException(String message) {
      super(message);
   }
}
