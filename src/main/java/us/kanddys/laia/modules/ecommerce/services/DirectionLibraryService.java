package us.kanddys.laia.modules.ecommerce.services;

import java.util.List;
import java.util.Map;

/**
 * Esta interface define las obligaciones de la clase DirectionLibraryService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public interface DirectionLibraryService {

   public List<Map<String, Object>> gDirectionLibraryCollectionItems(Long libraryCollectionId, String miniature);
}
