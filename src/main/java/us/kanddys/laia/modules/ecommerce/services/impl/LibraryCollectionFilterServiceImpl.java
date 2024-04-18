package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.modules.ecommerce.exception.LibraryCollectionNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.model.LibraryCollection;
import us.kanddys.laia.modules.ecommerce.model.TableCondition;
import us.kanddys.laia.modules.ecommerce.repository.LibraryCollectionJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.LibraryJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.TableConditionJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.BuyerLibraryService;
import us.kanddys.laia.modules.ecommerce.services.LibraryCollectionFilterService;

@Service
public class LibraryCollectionFilterServiceImpl implements LibraryCollectionFilterService {

   @Autowired
   private LibraryJpaRepository libraryJpaRepository;

   @Autowired
   private LibraryCollectionJpaRepository libraryCollectionJpaRepository;

   @Autowired
   private TableConditionJpaRepository tableConditionJpaRepository;

   @Autowired
   private BuyerLibraryService buyerLibraryService;

   @Override
   public Map<String, Object> gCollectionFilter(Long userId, Integer libraryType, Long libraryId) {
      checkLibraryType(userId, libraryType, libraryId);
      return null;
   }

   private List<Map<String, Object>> checkLibraryType(Long userId, Integer libraryType, Long libraryId) {
      switch (libraryType) {
         case 1:
            // ! AGREGAR BUCLE QUE VAYA PASANDO LAS TABLE CONDITIONS UNA POR UNA.
            getBuyerConditionsForLibraryId(null);
         case 2:

         default:
            throw new LibraryCollectionNotFoundException(ExceptionMessage.LIBRARY_COLLECTION_NOT_FOUND);
      }
   }

   /**
    * Método que tiene la obligación de obtener las condiciones de filtrado
    * perteneceintes a una colección de biblioteca.
    * 
    * @param tableCondition
    * @return
    */
   private Map<String, Object> getBuyerConditionsForLibraryId(TableCondition tableCondition) {
      Map<String, Object> conditionData = new HashMap<>();
      switch (tableCondition.getId().intValue()) {
         // ! Comprador.
         case 1:
            // TODO: TERMINAR.
            conditionData.put("id", tableCondition.getId());
            conditionData.put("title", tableCondition.getAlias());
            conditionData.put("type_condition", tableCondition.getTypeCondition());
            conditionData.put("props", buyerLibraryService.gTableConditionBuyerProperties(tableCondition.getId()));
            return null;
      }
      return null;
   }
}
