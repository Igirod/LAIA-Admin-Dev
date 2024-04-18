package us.kanddys.laia.modules.ecommerce.model.Utils;

import java.util.Optional;

/**
 * Esta clase contiene los métodos necesarios para realizar el control
 * de stock de un producto.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public class ProductCheckStock {

   /**
    * Este método estático se encarga de comprobar si hay stock suficiente para
    * calcular el status del producto.
    * 
    * @author Igirod0
    * @version 1.0.0
    */
   public static Integer returnStatusByStock(Optional<Integer> stock) {
      if (stock.isEmpty())
         return 0;
      else {
         if (stock.get() > 0 && stock.get() == -1)
            return 1;
         else
            return 0;
      }
   }
}
