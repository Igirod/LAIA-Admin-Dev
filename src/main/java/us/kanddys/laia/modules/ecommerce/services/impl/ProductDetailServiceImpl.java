package us.kanddys.laia.modules.ecommerce.services.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import us.kanddys.laia.modules.ecommerce.controller.dto.ProductDetailDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.ProductDetailShortDTO;
import us.kanddys.laia.modules.ecommerce.exception.IOJavaException;
import us.kanddys.laia.modules.ecommerce.exception.ProductNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.model.ProductDetail;
import us.kanddys.laia.modules.ecommerce.repository.ProductDetailJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.ProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.ProductDetailShortJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.ProductDetailService;
import us.kanddys.laia.modules.ecommerce.services.storage.FirebaseStorageService;

/**
 * Esta clase implementa las obligaciones de productDetailService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class ProductDetailServiceImpl implements ProductDetailService {

   @Autowired
   private ProductDetailJpaRepository productDetailJpaRepository;

   @Autowired
   private FirebaseStorageService firebaseStorageService;

   @Autowired
   private ProductJpaRepository productJpaRepository;

   @Autowired
   private ProductDetailShortJpaRepository productDetailShortJpaRepository;

   @Override
   public List<ProductDetailDTO> getProductDetailsByProductId(Long productId) {
      return productDetailJpaRepository.findProductDetailsByProductId(productId).stream().map(t -> {
         try {
            return new ProductDetailDTO(t);
         } catch (IOException e) {
            throw new IOJavaException(e.getMessage());
         }
      })
            .collect(Collectors.toList());
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public ProductDetailDTO createProductDetail(Optional<String> title, Optional<MultipartFile> frontPage,
         Long productId,
         Optional<String> description) {
      if (productJpaRepository.findProductIdIfExists(productId).isEmpty())
         throw new ProductNotFoundException(ExceptionMessage.PRODUCT_NOT_FOUND);
      try {
         return new ProductDetailDTO(productDetailJpaRepository.save(new ProductDetail(null, productId,
               title.orElse(null), description.orElse(null),
               (frontPage.isPresent())
                     ? firebaseStorageService.uploadFile(frontPage.get(),
                           "product-detail-" + productId.toString() + "-" + UUID.randomUUID().toString(),
                           "imageProducts")
                     : null,
               "IMAGE")));
      } catch (IOException e) {
         throw new IOJavaException(e.getMessage());
      }
   }

   @Override
   public ProductDetailShortDTO getProductDetailShort(Long productId) {
      return new ProductDetailShortDTO(productDetailShortJpaRepository.findProductDetailsByProductId(productId)
            .orElseThrow(() -> new ProductNotFoundException(ExceptionMessage.PRODUCT_NOT_FOUND)));
   }

   @Override
   public ProductDetailDTO createProductDetailFrontPageString(Optional<String> title, Optional<String> frontPage,
         Long productId, Optional<String> description) {
      try {
         return new ProductDetailDTO(
               productDetailJpaRepository.save(new ProductDetail(null, productId, title.orElse(null),
                     description.orElse(null), frontPage.orElse(null), "IMAGE")));
      } catch (IOException e) {
         throw new IOJavaException(e.getMessage());
      }
   }
}
