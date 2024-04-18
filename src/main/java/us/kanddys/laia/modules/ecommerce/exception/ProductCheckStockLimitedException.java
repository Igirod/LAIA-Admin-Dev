package us.kanddys.laia.modules.ecommerce.exception;

/**
 * Esta clase representa una excepción que se lanza cuando el stock de un producto
 * no es suficiente para realizar una compra.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public class ProductCheckStockLimitedException extends RuntimeException {
   
   public ProductCheckStockLimitedException(String message) {
      super(message);
   }
}
