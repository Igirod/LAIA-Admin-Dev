package us.kanddys.laia.modules.ecommerce.services;

import java.util.Map;

/**
 * Esta interface contiene las obligaciones que debe implementar la clase
 * LibraryCollectionFilterServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public interface LibraryCollectionFilterService {

   /**
    * Este método tiene la obligación de obtener un filtro de una colección.
    * 
    * @author igirod0
    * @version 1.0.0
    * @param collectionId
    * @return Map<String, Object>
    */
   public Map<String, Object> gCollectionFilter(Long userId, Integer libraryType, Long libraryId);
}
