package us.kanddys.laia.modules.ecommerce.exception;

/**
 * Esta clase se encarga de manejar las excepciones de las direcciones.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public class AddressNotFoundException extends RuntimeException {
   
   public AddressNotFoundException(String message) {
      super(message);
   }
}
