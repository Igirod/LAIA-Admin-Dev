package us.kanddys.laia.modules.ecommerce.services;

/**
 * Esta interface contiene las obligaciones que debe implementar la clase
 * KeyWordProductServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public interface KeyWordProductService {

   /**
    * Este método tiene la obligación de agregar una palabra clave a un producto.
    *
    * @param productId
    * @param keyWordId
    * @return Integer
    */
   public Integer createKeyWordProduct(Long productId, Long keyWordId);

   /**
    * Este método tiene la obligación de eliminar una keyword de un producto.
    *
    * @param keywordProductId
    * @return Integer
    */
   public Integer deleteKeyWordProduct(Long keywordId, Long productId);
}
