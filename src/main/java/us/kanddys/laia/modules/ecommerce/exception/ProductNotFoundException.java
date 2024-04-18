package us.kanddys.laia.modules.ecommerce.exception;

/**
 * Esta excepción se lanza cuando no se encuentra un producto.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public class ProductNotFoundException extends RuntimeException {
   public ProductNotFoundException(String message) {
      super(message);
   }
}
