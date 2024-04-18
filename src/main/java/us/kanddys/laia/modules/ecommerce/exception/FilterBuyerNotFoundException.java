package us.kanddys.laia.modules.ecommerce.exception;

/**
 * Esta clase representa una excepción que se lanza cuando no se encuentra un
 * filtro asociado a una colección de compradores.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public class FilterBuyerNotFoundException extends RuntimeException {

   private static final long serialVersionUID = 1L;

   public FilterBuyerNotFoundException(String message) {
      super(message);
   }
}
