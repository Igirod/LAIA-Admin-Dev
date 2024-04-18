package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import us.kanddys.laia.modules.ecommerce.services.DirectionLibraryService;

/**
 * Esta clase implementa las obligaciones de la interface
 * DirectionLibraryService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class DirectionLibraryServiceImpl implements DirectionLibraryService {

   @Override
   public List<Map<String, Object>> gDirectionLibraryCollectionItems(Long libraryCollectionId, String miniature) {
      return null;
   }

}
