package us.kanddys.laia.modules.ecommerce.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.modules.ecommerce.exception.ExistingHashtagProductException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.model.HashtagProduct;
import us.kanddys.laia.modules.ecommerce.model.HashtagProductId;
import us.kanddys.laia.modules.ecommerce.repository.HashtagProductCriteriaRepository;
import us.kanddys.laia.modules.ecommerce.repository.HashtagProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.HashtagProductService;

/**
 * Esta clase implementa las obligaciones de la interface HashtagProductService.
 * 
 * @auhtor Igirod0
 * @version 1.0.0
 */
@Service
public class HashtagProductServiceImpl implements HashtagProductService {

   @Autowired
   private HashtagProductJpaRepository hashtagProductJpaRepository;

   @Autowired
   private HashtagProductCriteriaRepository hashtagProductCriteriaRepository;

   @Override
   public Integer createHashtagProduct(Long idHashtag, Long idProduct) {
      if (hashtagProductJpaRepository.existsById(new HashtagProductId(idHashtag, idProduct)))
         throw new ExistingHashtagProductException(ExceptionMessage.EXISTING_HASHTAG_PRODUCT);
      hashtagProductJpaRepository.save(new HashtagProduct(new HashtagProductId(idHashtag, idProduct)));
      return 1;
   }

   @Override
   public Integer deleteHashtagProduct(Long idHashtag, Long idProduct) {
      hashtagProductJpaRepository.deleteById(new HashtagProductId(idHashtag, idProduct));
      return 1;
   }

   @Override
   public Integer deleteHashtagProductByProductId(Long idProduct) {
      hashtagProductCriteriaRepository.deleteHashtagProductsByProductId(idProduct);
      return 1;
   }
}
