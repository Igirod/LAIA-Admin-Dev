package us.kanddys.laia.modules.ecommerce.services;

/**
 * Esta interface contiene las obligaciones que debe implementar la clase
 * HashtagProductServiceImpl.
 * 
 * @auhtor Igirod0
 * @version 1.0.0
 */
public interface HashtagProductService {

   /**
    * Este método se encarga de crear un nuevo hashtag para un producto.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param idHashtag
    * @param idProduct
    * @return Integer
    */
   public Integer createHashtagProduct(Long idHashtag, Long idProduct);

   /**
    * Este método se encarga de eliminar un hashtag de un producto.
    * 
    * @param idHashtag
    * @param idProduct
    * @return Integer
    */
   public Integer deleteHashtagProduct(Long idHashtag, Long idProduct);

   /**
    * Este método borra todos los hashtag asociados a un producto.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param idProduct
    * @return Integer
    */
   public Integer deleteHashtagProductByProductId(Long idProduct);
}
