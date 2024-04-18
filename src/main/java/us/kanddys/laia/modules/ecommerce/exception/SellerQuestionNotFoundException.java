package us.kanddys.laia.modules.ecommerce.exception;

/**
 * Esta clase representa una excepción que se lanza cuando no se encuentra una
 * pregunta del vendedor.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public class SellerQuestionNotFoundException extends RuntimeException {

   private static final long serialVersionUID = 1L;

   public SellerQuestionNotFoundException(String message) {
      super(message);
   }
}
