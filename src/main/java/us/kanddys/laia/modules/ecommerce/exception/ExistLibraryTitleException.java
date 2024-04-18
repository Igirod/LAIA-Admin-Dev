package us.kanddys.laia.modules.ecommerce.exception;

/**
 * Esta clase se encarga de manejar las excepciones que se generan cuando se
 * intenta actualizar el titulo de una biblioteca y este ya existe.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public class ExistLibraryTitleException extends RuntimeException {

   private static final long serialVersionUID = 1L;

   public ExistLibraryTitleException(String message) {
      super(message);
   }
}
