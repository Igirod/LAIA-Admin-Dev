package us.kanddys.laia.modules.ecommerce.services.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import us.kanddys.laia.modules.ecommerce.controller.dto.ArticleDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.ArticleImageDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.HashtagDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.ManufacturingProductDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.ProductDTO;
import us.kanddys.laia.modules.ecommerce.exception.IOJavaException;
import us.kanddys.laia.modules.ecommerce.exception.MerchantNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.ProductNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.model.AuxiliarProduct;
import us.kanddys.laia.modules.ecommerce.model.ImageProduct;
import us.kanddys.laia.modules.ecommerce.model.KeyWord;
import us.kanddys.laia.modules.ecommerce.model.KeyWordProduct;
import us.kanddys.laia.modules.ecommerce.model.KeyWordProductId;
import us.kanddys.laia.modules.ecommerce.model.ManufacturingProduct;
import us.kanddys.laia.modules.ecommerce.model.Product;
import us.kanddys.laia.modules.ecommerce.model.Utils.DateUtils;
import us.kanddys.laia.modules.ecommerce.model.Utils.TypeFilter;
import us.kanddys.laia.modules.ecommerce.repository.AuxiliarMultipleQuestionJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.AuxiliarProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.AuxiliarProductKeyWordJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.AuxiliarProductMediaJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.HashtagJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.HashtagProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.ImageProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.InvenstmentJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.KeyWordJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.KeyWordProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.ManufacturingProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.ProductCriteriaRepository;
import us.kanddys.laia.modules.ecommerce.repository.ProductDetailJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.ProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.SellerQuestionJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.UserJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.HashtagProductService;
import us.kanddys.laia.modules.ecommerce.services.HashtagService;
import us.kanddys.laia.modules.ecommerce.services.ImageProductService;
import us.kanddys.laia.modules.ecommerce.services.InvenstmentService;
import us.kanddys.laia.modules.ecommerce.services.ManufacturingProductService;
import us.kanddys.laia.modules.ecommerce.services.ProductDetailService;
import us.kanddys.laia.modules.ecommerce.services.ProductService;
import us.kanddys.laia.modules.ecommerce.services.SellerQuestionService;
import us.kanddys.laia.modules.ecommerce.services.storage.FirebaseStorageService;

/**
 * Esta clase implementa las obligaciones de ProductService.
 * 
 * @author Igirod0
 * @version 1.0.2
 */
@Service
public class ProductServiceImpl implements ProductService {

   @Autowired
   private ProductCriteriaRepository productCriteriaRepository;

   @Autowired
   private ProductJpaRepository productJpaRepository;

   @Autowired
   private FirebaseStorageService firebaseStorageService;

   @Autowired
   private ManufacturingProductService manufacturingProductService;

   @Autowired
   private InvenstmentService invenstmentService;

   @Autowired
   private ProductDetailService productDetailService;

   @Autowired
   private HashtagService hashtagService;

   @Autowired
   private HashtagProductService hashtagProductService;

   @Autowired
   private ImageProductService imageProductService;

   @Autowired
   private UserJpaRepository userJpaRepository;

   @Autowired
   private SellerQuestionService sellerQuestionService;

   @Autowired
   private AuxiliarProductJpaRepository auxiliarProductJpaRepository;

   @Autowired
   private AuxiliarMultipleQuestionJpaRepository auxiliarMultipleQuestionRepository;

   @Autowired
   private AuxiliarProductMediaJpaRepository auxiliarProductMediaRepository;

   @Autowired
   private AuxiliarProductKeyWordJpaRepository auxiliarProductKeyWordJpaRepository;

   @Autowired
   private KeyWordJpaRepository keyWordJpaRepository;

   @Autowired
   private KeyWordProductJpaRepository keyWordProductJpaRepository;

   @Autowired
   private InvenstmentJpaRepository invenstmentJpaRepository;

   @Autowired
   private ProductDetailJpaRepository productDetailJpaRepository;

   @Autowired
   private SellerQuestionJpaRepository sellerQuestionJpaRepository;

   @Autowired
   private ManufacturingProductJpaRepository manufacturingProductJpaRepository;

   @Autowired
   private HashtagJpaRepository hashtagJpaRepository;

   @Autowired
   private HashtagProductJpaRepository hashtagProductJpaRepository;

   @Autowired
   private ImageProductJpaRepository imageProductJpaRepository;

   @Override
   public ProductDTO getProductById(Long productId) {
      try {
         return new ProductDTO(productJpaRepository.findById(productId)
               .orElseThrow(() -> new ProductNotFoundException(ExceptionMessage.PRODUCT_NOT_FOUND)));
      } catch (ProductNotFoundException e) {
         throw new ProductNotFoundException(ExceptionMessage.PRODUCT_NOT_FOUND);
      } catch (IOException e) {
         throw new IOJavaException(e.getMessage());
      }
   }

   @Override
   public List<ProductDTO> getProductsPaginated(Integer page, Long merchantId, Optional<Integer> status) {
      return productCriteriaRepository.findProductsPaginated(page, merchantId, status).stream().map(t -> {
         try {
            return new ProductDTO(t);
         } catch (IOException e) {
            throw new IOJavaException(e.getMessage());
         }
      }).collect(Collectors.toList());
   }

   @Override
   public List<ProductDTO> getProductsByTypeFilterPaginated(Integer page, TypeFilter typeFilter) {
      return productCriteriaRepository.findProductsByTypeFilterPaginated(page, typeFilter).stream().map(t -> {
         try {
            return new ProductDTO(t);
         } catch (IOException e) {
            throw new IOJavaException(e.getMessage());
         }
      }).collect(Collectors.toList());
   }

   @Override
   public String updateFrontPage(Long productId, MultipartFile image) {
      var url = firebaseStorageService.uploadFile(image, "front-page-product-", "frontPages");
      productJpaRepository.updateFrontPage(productId,
            url);
      return url;
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer updateProduct(Long productId, Optional<String> title, Optional<Double> price, Optional<Integer> stock,
         Optional<Integer> status, Optional<String> typeOfSale) {
      var product = productJpaRepository.findById(productId);
      if (product.isPresent()) {
         var productToUpdate = product.get();
         title.ifPresent(productToUpdate::setTitle);
         price.ifPresent(productToUpdate::setPrice);
         stock.ifPresent(productToUpdate::setStock);
         status.ifPresent(productToUpdate::setStatus);
         typeOfSale.ifPresent(productToUpdate::setTypeOfSale);
         productJpaRepository.save(productToUpdate);
         return 1;
      }
      return 0;
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer deleteProduct(Long productId) {
      productJpaRepository.deleteById(productId);
      return 1;
   }

   private ProductDTO createProductAndDTO(Product product, MultipartFile frontPage) {
      try {
         var productDTO = new ProductDTO(productJpaRepository.save(product));
         // ! Carga solo la portada.
         if (frontPage != null)
            productDTO.setFrontPage(updateFrontPage(productDTO.getId(), frontPage));
         return productDTO;
      } catch (IOException e) {
         throw new IOJavaException(e.getMessage());
      }
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public ProductDTO createProduct(Optional<MultipartFile> frontPage, Optional<String> title,
         Optional<String> typeOfSale, Optional<String> price, Optional<String> stock, Optional<String> status,
         Optional<String> userId, Optional<String> manufacturingTime, Optional<String> invenstmentNote,
         Optional<String> invenstmentAmount, Optional<String> invenstmentTitle, Optional<String> manufacturingType,
         Optional<String> segmentTitle, Optional<String> segmentDescription, Optional<MultipartFile> segmentMedia,
         Optional<String> hashtagValue, Optional<List<String>> keywords, Optional<String> sellerQuestionValue,
         Optional<String> sellerQuestionType, Optional<String> sellerQuestionLimit,
         Optional<String> sellerQuestionRequired, Optional<String> typeOfPrice,
         Optional<List<String>> sellerQuestionOptions) {
      var userid = Long.valueOf(userId.get());
      var merchantId = userJpaRepository.existByUserId(userid);
      ProductDTO newProductDTO = null;
      if (merchantId == null)
         throw new MerchantNotFoundException(ExceptionMessage.MERCHANT_NOT_FOUND);
      else {
         // * Se crea el producto asociado a un merchant.
         try {
            newProductDTO = createProductAndDTO(
                  new Product(null, (!title.isEmpty() ? title.get() : null),
                        (!price.isEmpty() ? Double.valueOf(price.get()) : null),
                        (!stock.isEmpty() ? Integer.valueOf(stock.get()) : null), null,
                        merchantId, (!status.isEmpty() ? Integer.valueOf(status.get()) : null),
                        DateUtils.getCurrentDate(), (!typeOfSale.isEmpty() ? typeOfSale.get() : null)),
                  (!frontPage.isEmpty() ? frontPage.get() : null));
            createProductExtraAtributes(Optional.of(newProductDTO.getId().toString()), manufacturingTime,
                  invenstmentAmount, invenstmentNote, invenstmentTitle, manufacturingType, segmentTitle,
                  segmentDescription, segmentMedia, hashtagValue, keywords, sellerQuestionValue,
                  sellerQuestionType, sellerQuestionLimit, sellerQuestionRequired, sellerQuestionOptions, userid);
         } catch (ParseException e) {
            throw new RuntimeException("Error al convertir la fecha");
         }
      }
      return newProductDTO;
   }

   private void createProductExtraAtributes(Optional<String> productId, Optional<String> manufacturingTime,
         Optional<String> invenstmentAmount, Optional<String> invenstmentNote, Optional<String> invenstmentTitle,
         Optional<String> manufacturingType, Optional<String> segmentTitle, Optional<String> segmentDescription,
         Optional<MultipartFile> segmentMedia, Optional<String> hashtagValue, Optional<List<String>> keywords,
         Optional<String> sellerQuestionValue,
         Optional<String> sellerQuestionType, Optional<String> sellerQuestionLimit,
         Optional<String> sellerQuestionRequired, Optional<List<String>> sellerQuestionOptions, Long userId) {
      if (manufacturingTime.isPresent() && manufacturingType.isPresent()) {
         manufacturingProductService.createManufacturingProduct(Long.valueOf(productId.get()),
               manufacturingType, Optional.of(Integer.valueOf(manufacturingTime.get())));
      }
      if (invenstmentAmount.isPresent() || invenstmentNote.isPresent() || invenstmentTitle.isPresent()) {
         invenstmentService.createInvenstment(Long.valueOf(productId.get()),
               Optional.of(Double.valueOf(invenstmentAmount.get())), invenstmentNote, invenstmentTitle);
      }
      if (segmentDescription.isPresent() || segmentMedia.isPresent() || segmentTitle.isPresent()) {
         productDetailService.createProductDetail(segmentTitle, segmentMedia, Long.valueOf(productId.get()),
               segmentDescription);
      }
      if (hashtagValue.isPresent()) {
         var hashtagId = hashtagService.getHashtagIdByValue(hashtagValue.get());
         // * Si no existe el hashtag se crea.
         if (hashtagId == null) {
            hashtagProductService.createHashtagProduct(hashtagService.createHashtag(hashtagValue.get()),
                  Long.valueOf(productId.get()));
         } else {
            hashtagProductService.createHashtagProduct(hashtagId, Long.valueOf(productId.get()));
         }
      }
      if (keywords.isPresent()) {
         var existKeyWords = keyWordJpaRepository.findKeyWordIdByWords(keywords.get());
         var newKeyWordsWord = keywords.get().stream()
               .filter(
                     t -> !keyWordJpaRepository.findKeyWordIdByWords(keywords.get()).stream().map(KeyWord::getWord)
                           .collect(Collectors.toList()).contains(t))
               .collect(Collectors.toList());
         // * Si no existe la keyword se crea.
         if (!newKeyWordsWord.isEmpty()) {
            List<Long> existProductKeyWordsId = keyWordJpaRepository.saveAll(newKeyWordsWord.stream()
                  .map(t -> new KeyWord(t, userId)).collect(Collectors.toList())).stream().map(KeyWord::getId)
                  .collect(Collectors.toList());
            existProductKeyWordsId.addAll(existKeyWords.stream().map(KeyWord::getId).collect(Collectors.toList()));
            // * Se asocian las palabras existentes al nuevo producto.
            var l = existProductKeyWordsId.stream()
                  .map(t -> new KeyWordProduct(new KeyWordProductId(t, Long.valueOf(productId.get()))))
                  .collect(Collectors.toList());
            keyWordProductJpaRepository.saveAll(l);
         } else {
            // * Si existen todas las palabras se asocian al nuevo producto.
            keyWordProductJpaRepository.saveAll(existKeyWords.stream().map(KeyWord::getId)
                  .map(t -> new KeyWordProduct(new KeyWordProductId(t, Long.valueOf(productId.get()))))
                  .collect(Collectors.toList()));
         }
      }
      if (sellerQuestionValue.isPresent() && sellerQuestionType.isPresent()) {
         sellerQuestionService.createQuestion(sellerQuestionValue.get(),
               (sellerQuestionRequired.isPresent() ? Optional.of(Integer.valueOf(sellerQuestionRequired.get())) : null),
               sellerQuestionType.get(),
               (sellerQuestionLimit.isPresent() ? Optional.of(Integer.valueOf(sellerQuestionLimit.get())) : null),
               Long.valueOf(productId.get()),
               sellerQuestionOptions);
      }
      // if (categoryTitle.isPresent()) {
      // var categoryId = categoryService.getCategoryIdByTitle(categoryTitle.get());
      // if (categoryId == null) {
      // categoryProductService.createCategoryProduct(categoryService.createCategory(categoryTitle.get()),
      // Long.valueOf(productId.get()));
      // } else {
      // categoryProductService.createCategoryProduct(categoryId,
      // Long.valueOf(productId.get()));
      // }
      // }
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Long createProduct(Optional<Long> userId, Optional<Long> productId, String title, String tStock, Double price,
         Integer stock) {
      if (productId.isPresent() && userId.isPresent()) {
         productJpaRepository.updateMerchantId(productId.get(), userId.get());
         return productId.get();
      }
      if (productId.isEmpty()) {
         Long existUserId = null;
         if (userId.isPresent()) {
            existUserId = userId.get();
         }
         try {
            return productJpaRepository.save(new Product(null, title, price, stock, null, existUserId, 1,
                  DateUtils.getCurrentDate(), null)).getId();
         } catch (ParseException e) {
            throw new RuntimeException("Error al convertir la fecha");
         }
      }
      return -1L;
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Long updateAdminSellAssociation(Long productId, Long userId) {
      Optional<AuxiliarProduct> auxiliarProduct = auxiliarProductJpaRepository.findById(productId);
      Product product = null;
      if (auxiliarProduct.isPresent()) {
         product = null;
         try {
            product = new Product(null,
                  (auxiliarProduct.get().getTitle() != null ? auxiliarProduct.get().getTitle() : null),
                  (auxiliarProduct.get().getPrice() != null ? auxiliarProduct.get().getPrice() : null),
                  (auxiliarProduct.get().getStock() != null ? auxiliarProduct.get().getStock() : null),
                  (auxiliarProduct.get().getFrontPage() != null ? auxiliarProduct.get().getFrontPage() : null), userId,
                  1, DateUtils.getCurrentDate(),
                  (auxiliarProduct.get().getTypeOfStock() != null ? auxiliarProduct.get().getTypeOfStock() : null));
         } catch (ParseException e) {
            throw new RuntimeException("Error al convertir la fecha");
         }
         product = productJpaRepository.save(product);
         var auxiliarProductKeyWords = auxiliarProductKeyWordJpaRepository.findByAuxiliarProductId(productId);
         createProductExtraAtributes(Optional.of(product.getId().toString()),
               (auxiliarProduct.get().getManufacturingTime() != null
                     ? Optional.of(auxiliarProduct.get().getManufacturingTime().toString())
                     : Optional.empty()),
               (auxiliarProduct.get().getInvenstmentAmount() != null
                     ? Optional.of(auxiliarProduct.get().getInvenstmentAmount().toString())
                     : Optional.empty()),
               (auxiliarProduct.get().getManufacturingTime() != null
                     ? Optional.of(auxiliarProduct.get().getInvenstmentNote().toString())
                     : Optional.empty()),
               (auxiliarProduct.get().getInvenstmentTitle() != null
                     ? Optional.of(auxiliarProduct.get().getInvenstmentTitle().toString())
                     : Optional.empty()),
               (auxiliarProduct.get().getManufacturingType() != null
                     ? Optional.of(auxiliarProduct.get().getManufacturingType().toString())
                     : Optional.empty()),
               (auxiliarProduct.get().getSegmentTitle() != null
                     ? Optional.of(auxiliarProduct.get().getSegmentTitle().toString())
                     : Optional.empty()),
               (auxiliarProduct.get().getSegmentDescription() != null
                     ? Optional.of(auxiliarProduct.get().getSegmentDescription().toString())
                     : Optional.empty()),
               Optional.empty(),
               (auxiliarProduct.get().getHashtag() != null ? Optional.of(auxiliarProduct.get().getHashtag())
                     : Optional.empty()),
               (!auxiliarProductKeyWords.isEmpty()
                     ? Optional.of(auxiliarProductKeyWords)
                     : Optional.empty()),
               (auxiliarProduct.get().getQuestionTitle() != null
                     ? Optional.of(auxiliarProduct.get().getQuestionTitle().toString())
                     : Optional.empty()),
               (auxiliarProduct.get().getQuestionType() != null
                     ? Optional.of(auxiliarProduct.get().getQuestionType().toString())
                     : Optional.empty()),
               (auxiliarProduct.get().getQuestionLimit() != null
                     ? Optional.of(auxiliarProduct.get().getQuestionLimit().toString())
                     : Optional.empty()),
               (auxiliarProduct.get().getQuestionRequired() != null
                     ? Optional.of(auxiliarProduct.get().getQuestionRequired().toString())
                     : Optional.empty()),
               Optional.of(auxiliarMultipleQuestionRepository.findOptionsByProductId(productId)), userId);
         productDetailService.createProductDetailFrontPageString((auxiliarProduct.get().getSegmentTitle() != null
               ? Optional.of(auxiliarProduct.get().getSegmentTitle().toString())
               : Optional.empty()),
               (auxiliarProduct.get().getSegmentMedia() != null
                     ? Optional.of(auxiliarProduct.get().getSegmentMedia().toString())
                     : Optional.empty()),
               product.getId(), (auxiliarProduct.get().getSegmentDescription() != null
                     ? Optional.of(auxiliarProduct.get().getSegmentDescription().toString())
                     : Optional.empty()));
      } else {
         throw new ProductNotFoundException(ExceptionMessage.PRODUCT_NOT_FOUND);
      }
      // ! Borrado del producto auxiliar.
      auxiliarMultipleQuestionRepository.deleteOptionsByProductId(productId);
      auxiliarProductMediaRepository.deleteByAuxiliarProductId(productId);
      auxiliarProductKeyWordJpaRepository.deleteWordsByProductId(productId);
      auxiliarProductJpaRepository.deleteById(productId);
      return product.getId();
   }

   @Override
   public ArticleDTO getAdminSellProduct(Long id) {
      ProductDTO productDTO = getProductById(id);
      if (productDTO == null)
         throw new ProductNotFoundException(ExceptionMessage.PRODUCT_NOT_FOUND);
      ArticleDTO articleDTO = new ArticleDTO();
      articleDTO.setProductId(id);
      List<String> medias = new ArrayList<String>();
      String frontPage = productJpaRepository.findFrontPageByProductId(id);
      if (frontPage != null)
         medias.add(frontPage);
      medias.addAll(imageProductService.getImagesProductByProductId(id).stream().map(t -> t.getUrl())
            .collect(Collectors.toList()));
      articleDTO.setMedias(medias);
      articleDTO.setInvenstmentsCount(invenstmentJpaRepository.countInvenstmentsByProductId(id));
      ManufacturingProduct manufacturingProduct = manufacturingProductJpaRepository.findByProductId(id);
      articleDTO.setManufacturingProduct(
            ((manufacturingProduct != null ? new ManufacturingProductDTO(manufacturingProduct) : null)));
      articleDTO.setTitle(productDTO.getTitle());
      articleDTO.setPrice(productDTO.getPrice());
      articleDTO.setTypeOfPrice(productDTO.getTypeOfPrice());
      articleDTO.setStock(productDTO.getStock());
      articleDTO.setSegments(productDetailJpaRepository.countProductDetailsByProductId(id));
      Long hashtagId = hashtagProductJpaRepository.findByProductId(id);
      if (hashtagId != null)
         articleDTO.setHashtag(new HashtagDTO(hashtagJpaRepository.findById(hashtagId).get()));
      else {
         articleDTO.setHashtag(null);
      }
      articleDTO.setKeywords(keyWordProductJpaRepository.countKeyWordProductByProductId(id));
      articleDTO.setQuestions(sellerQuestionJpaRepository.countQuestionsByProductId(id));
      return articleDTO;
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public List<ArticleImageDTO> updateAdminSellProductMedia(Long productId, String index, Optional<String> title,
         Optional<String> price,
         Optional<String> tPrice, Optional<String> stock, Optional<String> tStock,
         List<String> existImages,
         List<MultipartFile> newImages) {
      String[] indexArray = index.split(" ");
      Optional<Product> product = productJpaRepository.findById(productId);
      if (product.isEmpty())
         throw new ProductNotFoundException(ExceptionMessage.PRODUCT_NOT_FOUND);
      List<ArticleImageDTO> newArticleImagesDTOs = new ArrayList<ArticleImageDTO>();
      List<ImageProduct> newImageProducts = new ArrayList<ImageProduct>();
      var productToUpdate = product.get();
      if (indexArray[0].equals("E")) {
         newImages.forEach(t -> {
            ArticleImageDTO articleImageDTO = new ArticleImageDTO(
                  firebaseStorageService.uploadFile(t,
                        "image-product-" + productToUpdate.getId().toString() + "-" + UUID.randomUUID().toString(),
                        "imageProducts"),
                  "IMAGE");
            newArticleImagesDTOs.add(articleImageDTO);
            newImageProducts
                  .add(
                        new ImageProduct(null, productToUpdate.getId(), articleImageDTO.getUrl(), "IMAGE"));
         });
      }
      if (indexArray[0].equals("N")) {
         // * Se sube a Firebase la nueva portada del producto.
         newArticleImagesDTOs.add(new ArticleImageDTO(
               firebaseStorageService.uploadFile(newImages.get(0),
                     "front-page-product-" + productToUpdate.getId().toString(),
                     "frontPages"),
               "IMAGE"));
         productJpaRepository.updateFrontPage(productId, newArticleImagesDTOs.get(0).getUrl());
         newImages.stream().skip(1).forEach(t -> {
            ArticleImageDTO articleImageDTO = new ArticleImageDTO(
                  firebaseStorageService.uploadFile(t,
                        "image-product-" + productToUpdate.getId().toString() + "-" + UUID.randomUUID().toString(),
                        "imageProducts"),
                  "IMAGE");
            newArticleImagesDTOs.add(articleImageDTO);
            newImageProducts
                  .add(
                        new ImageProduct(null, productToUpdate.getId(), articleImageDTO.getUrl(), "IMAGE"));
         });
      }
      // * Se guardan las nuevas imagenes.
      imageProductJpaRepository.saveAll(newImageProducts);
      List<ArticleImageDTO> responseArticleMedias = new ArrayList<>();
      var indexE = 0;
      var indexN = 0;
      for (String existIndex : indexArray) {
         if (existIndex.equals("E")) {
            responseArticleMedias
                  .add(new ArticleImageDTO(existImages.get(indexE), "IMAGE"));
            indexE = indexE + 1;
         }
         if (existIndex.equals("N")) {
            responseArticleMedias.add(new ArticleImageDTO(
                  newArticleImagesDTOs.get(indexN).getUrl(), newArticleImagesDTOs.get(indexN).getType()));
            indexN = indexN + 1;
         }
      }
      title.ifPresent(productToUpdate::setTitle);
      price.ifPresent(t -> productToUpdate.setPrice(Double.valueOf(t)));
      stock.ifPresent(t -> productToUpdate.setStock(Integer.valueOf(t)));
      tStock.ifPresent(productToUpdate::setTypeOfSale);
      productJpaRepository.save(productToUpdate);
      return responseArticleMedias;
   }
}
