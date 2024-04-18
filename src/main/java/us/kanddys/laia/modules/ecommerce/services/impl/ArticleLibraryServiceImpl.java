package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.modules.ecommerce.model.Product;
import us.kanddys.laia.modules.ecommerce.repository.ProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.ArticleLibraryService;

/**
 * Esta clase implementa las obligaciones de la interface ArticleLibraryService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class ArticleLibraryServiceImpl implements ArticleLibraryService {

   @Autowired
   private ProductJpaRepository productJpaRepository;

   @Override
   public List<Map<String, Object>> gArticleLibraryCollectionItems(Long libraryCollectionId, String miniature) {
      // TODO: FALTA DEFINIR LOS FILTRADOS.
      String miniatureTest = "PRICE-STOCK-PRICE STOCK";
      List<String> properties = new ArrayList<String>();
      properties = Arrays.asList(miniatureTest.split("-")).stream()
            .flatMap(miniatureProperty -> Arrays.stream(miniatureProperty.split(" ")))
            .collect(Collectors.toList());
      return getArticleCollectionsItemsByMiniature(properties.stream().distinct().collect(Collectors.toList()));

   }

   /**
    * Este método obtiene los items de la colección de artículos perteneciente a
    * una biblioteca.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param miniatureProperties
    * @return List<Map<String, Object>>
    */
   private List<Map<String, Object>> getArticleCollectionsItemsByMiniature(List<String> miniatureProperties) {
      List<Product> products = productJpaRepository.findAll();
      List<Map<String, Object>> itemsCollectionResponse = new ArrayList<Map<String, Object>>();
      for (Product product : products) {
         Map<String, Object> itemCollectionData = new HashMap<String, Object>();
         if (miniatureProperties.contains("PRICE")) {
            itemCollectionData.put("price", product.getPrice());
         }
         if (miniatureProperties.contains("STOCK")) {
            itemCollectionData.put("stock", product.getStock());
         }
         if (miniatureProperties.contains("MERCHANTID")) {
            itemCollectionData.put("merchantId", product.getMerchantId());
         }
         if (miniatureProperties.contains("STATUS")) {
            itemCollectionData.put("status", product.getStatus());
         }
         if (miniatureProperties.contains("CREATEDAT")) {
            itemCollectionData.put("createdAt", product.getCreatedAt());
         }
         if (miniatureProperties.contains("TYPEOFSALE")) {
            itemCollectionData.put("typeOfSale", product.getTypeOfSale());
         }
         itemsCollectionResponse.add(itemCollectionData);
      }
      return itemsCollectionResponse;
   }

}
