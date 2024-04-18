package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import us.kanddys.laia.modules.ecommerce.controller.dto.HashtagDTO;
import us.kanddys.laia.modules.ecommerce.exception.ExistingHashtagException;
import us.kanddys.laia.modules.ecommerce.exception.HashtagNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.model.Hashtag;
import us.kanddys.laia.modules.ecommerce.repository.HashtagJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.HashtagProductCriteriaRepository;
import us.kanddys.laia.modules.ecommerce.services.HashtagProductService;
import us.kanddys.laia.modules.ecommerce.services.HashtagService;

/**
 * Esta clase implementa las obligaciones de la interface HashtagService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class HashtagServiceImpl implements HashtagService {

   @Autowired
   private HashtagJpaRepository hashtagJpaRepository;

   @Autowired
   private HashtagProductCriteriaRepository hashtagProductCriteriaRepository;

   @Autowired
   private HashtagProductService hashtagProductService;

   @Override
   public Long getHashtagIdByValue(String value) {
      return hashtagJpaRepository.findByValue(value);
   }

   @Override
   public Long createHashtag(String hashtag) {
      if (hashtagJpaRepository.findByValue(hashtag) != null)
         throw new ExistingHashtagException(ExceptionMessage.EXISTING_HASHTAG);
      return hashtagJpaRepository.save(new Hashtag(null, hashtag)).getId();
   }

   @Override
   public Integer deleteHashtag(Long id) {
      hashtagJpaRepository.deleteById(id);
      return 1;
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer updateHashtag(Long id, String hashtag) {
      var existHashtag = hashtagJpaRepository.findById(id);
      if (existHashtag.isEmpty())
         throw new HashtagNotFoundException(ExceptionMessage.HASHTAG_NOT_FOUND);
      if (hashtag != null) {
         var hashtagToUpdate = existHashtag.get();
         hashtagToUpdate.setValue(hashtag);
         hashtagJpaRepository.save(hashtagToUpdate);
      } else {
         deleteHashtag(id);
      }
      return 1;
   }

   @Override
   public HashtagDTO getHashtagsByProductId(Long productId) {
      var hashtag = hashtagJpaRepository.findById(hashtagProductCriteriaRepository.getHashtagIdsByProductId(productId));
      if (hashtag.isEmpty())
         return new HashtagDTO();
      else
         return new HashtagDTO(hashtag.get());
   }

   @Override
   public Integer wAdminSellHashtag(String hashtag) {
      var existHashtag = hashtagJpaRepository.findByValue(hashtag);
      if (existHashtag == null)
         return 0;
      else
         return 1;
   }

   @Override
   public Integer uAdminSellHashtag(Long productId, Optional<String> hashtag) {
      if (hashtag.isPresent()) {
         var existHashtag = hashtagJpaRepository.findByValue(hashtag.get());
         if (existHashtag == null)
            return 0;
         else {
            hashtagProductService.createHashtagProduct(existHashtag, productId);
            return 1;
         }
      } else {
         hashtagProductService.deleteHashtagProductByProductId(productId);
         return -1;
      }
   }
}
