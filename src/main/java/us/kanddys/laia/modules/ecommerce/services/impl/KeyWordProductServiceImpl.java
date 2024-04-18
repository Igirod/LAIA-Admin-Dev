package us.kanddys.laia.modules.ecommerce.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.modules.ecommerce.model.KeyWordProduct;
import us.kanddys.laia.modules.ecommerce.model.KeyWordProductId;
import us.kanddys.laia.modules.ecommerce.repository.KeyWordProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.KeyWordProductService;

/**
 * Esta clase contiene las implementaciones de las obligaciones de la interface
 * KeyWordService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class KeyWordProductServiceImpl implements KeyWordProductService {

   @Autowired
   private KeyWordProductJpaRepository keyWordProductJpaRepository;

   @Override
   public Integer createKeyWordProduct(Long productId, Long keyWordId) {
      keyWordProductJpaRepository.save(new KeyWordProduct(new KeyWordProductId(keyWordId, productId)));
      return 1;
   }

   @Override
   public Integer deleteKeyWordProduct(Long keywordId, Long productId) {
      keyWordProductJpaRepository.deleteById(new KeyWordProductId(keywordId, productId));
      return 1;
   }
}
