package us.kanddys.laia.modules.ecommerce.exception;

public class CategoryNotFoundException extends RuntimeException {

   public CategoryNotFoundException(String message) {
      super(message);
   }
}
