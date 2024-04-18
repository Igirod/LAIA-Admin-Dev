package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import us.kanddys.laia.modules.ecommerce.controller.dto.ImageProductDTO;
import us.kanddys.laia.modules.ecommerce.exception.ProductNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.model.ImageProduct;
import us.kanddys.laia.modules.ecommerce.model.Product;
import us.kanddys.laia.modules.ecommerce.repository.ImageProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.ProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.ImageProductService;
import us.kanddys.laia.modules.ecommerce.services.storage.FirebaseStorageService;

/**
 * Esta clase implementa las obligaciones de ImageProductService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class ImageProductServiceImpl implements ImageProductService {

   @Autowired
   private ImageProductJpaRepository imageProductJpaRepository;

   @Autowired
   private ProductJpaRepository productJpaRepository;

   @Autowired
   private FirebaseStorageService firebaseStorageService;

   @Transactional(rollbackOn = Exception.class)
   @Override
   public ImageProductDTO uploadImageProduct(MultipartFile multipartFile, Long productId) {
      if (productJpaRepository.findProductIdIfExists(productId).isEmpty())
         throw new ProductNotFoundException(ExceptionMessage.PRODUCT_NOT_FOUND);
      return new ImageProductDTO(imageProductJpaRepository
            .save(new ImageProduct(null, productId,
                  firebaseStorageService.uploadFile(multipartFile,
                        "image-product-" + productId.toString() + "-" + UUID.randomUUID().toString(),
                        "imageProducts"),
                  "IMAGE")));
   }

   @Override
   public List<ImageProductDTO> getImagesProductByProductId(Long productId) {
      return imageProductJpaRepository.findAllByProductId(productId).stream().map(ImageProductDTO::new)
            .toList();
   }

   @Override
   public Long uploadImagesProducts(List<MultipartFile> medias, Optional<String> productId) {
      if (productId.isPresent()) {
         if (productJpaRepository.existsById(Long.valueOf(productId.get()))) {
            medias.stream().map(image -> imageProductJpaRepository
                  .save(new ImageProduct(null, Long.valueOf(productId.get()),
                        firebaseStorageService.uploadFile(image, "image-product-" + productId.get(), "imageProducts")
                              + "-" + UUID.randomUUID().toString(),
                        "IMAGE")))
                  .toList();
            return Long.valueOf(productId.get());
         }
      } else {
         Long newProductId = productJpaRepository.save(new Product()).getId();
         medias.stream().map(image -> imageProductJpaRepository
               .save(new ImageProduct(null, newProductId,
                     firebaseStorageService.uploadFile(image,
                           "image-product-" + newProductId.toString() + "-" + UUID.randomUUID().toString(),
                           "imageProducts"),
                     "IMAGE")))
               .toList();
         return newProductId;
      }
      return -1L;
   }
}
