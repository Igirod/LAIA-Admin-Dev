package us.kanddys.laia.modules.ecommerce.services.check;

/**
 * Esta clase contiene las obligaciones que debe implementar la clase
 * InvoiceProductCheckStockService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public interface ProductCheckStockService {

   /**
    * Este método verifica si hay suficiente stock para el producto.
    *
    * @param productId
    * @param stock
    * @param quantity
    * @return boolean
    */
   public boolean checkStock(Long productId, Integer stock, Integer quantity);

   /**
    * Este método verifica actualiza el stock del producto en base a la
    * cantidad de un producto reservada.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param productId
    * @param stock
    * @param quantity
    */
   public void updateStockByProductQuantity(Long productId, Integer stock, Integer quantity);
}
