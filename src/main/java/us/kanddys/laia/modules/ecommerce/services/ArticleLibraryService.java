package us.kanddys.laia.modules.ecommerce.services;

import java.util.List;
import java.util.Map;

/**
 * Esta clase contiene las obligaciones que debe implementar la clase
 * ArticleLibraryServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public interface ArticleLibraryService {

   /**
    * Este método se encarga de obtener los items de una colección de una
    * biblioteca segun una miniatura pre definida.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param libraryCollectionId
    * @param miniature
    * @return List<Map<String, Object>>
    */
   public List<Map<String, Object>> gArticleLibraryCollectionItems(Long libraryCollectionId, String miniature);
}
