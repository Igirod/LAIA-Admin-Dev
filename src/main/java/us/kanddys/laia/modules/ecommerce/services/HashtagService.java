package us.kanddys.laia.modules.ecommerce.services;

import java.util.Optional;

import us.kanddys.laia.modules.ecommerce.controller.dto.HashtagDTO;

/**
 * Esta interface contiene las obligaciones que debe implementar la clase
 * HashtagServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public interface HashtagService {

   /**
    * Este método se encarga de obtener un hashtag por su id.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param id El id del hashtag.
    * @return Integer
    */
   public Long getHashtagIdByValue(String value);

   /**
    * Este método se encarga de crear un hashtag.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param hashtag El hashtag a crear.
    * @return Long
    */
   public Long createHashtag(String hashtag);

   /**
    * Este método se encarga de eliminar un hashtag.
    * 
    * @param id El id del hashtag.
    * @return Integer
    */
   public Integer deleteHashtag(Long id);

   /**
    * Este método se encarga de modificar un hashtag.
    *
    * @param id      El id del hashtag.
    * @param hashtag El nuevo hashtag.
    * @return String
    */
   public Integer updateHashtag(Long id, String hashtag);

   /**
    * Este método se encarga de obtener todos los hashtags asociados a un producto.
    *
    * @param productId El id del producto.
    * @return List
    */
   public HashtagDTO getHashtagsByProductId(Long productId);

   /**
    * Método que verifica la existencia del hashtag.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param hashtag
    * @return Integer
    */
   public Integer wAdminSellHashtag(String hashtag);

   /**
    * Este método tiene la obligación de actualizar el hashtag de un producto.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param productId
    * @param hashtag
    * @return Integer
    */
   public Integer uAdminSellHashtag(Long productId, Optional<String> hashtag);
}
