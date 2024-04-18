package us.kanddys.laia.modules.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.web.multipart.MultipartFile;

import us.kanddys.laia.modules.ecommerce.controller.dto.ArticleDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.ArticleImageDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.ProductDTO;
import us.kanddys.laia.modules.ecommerce.exception.ProductNotFoundException;
import us.kanddys.laia.modules.ecommerce.model.Utils.TypeFilter;

/**
 * Esta interface contiene las obligaciones que debe implementar la
 * clase ProductServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.5
 */
public interface ProductService {

   /**
    * Este método se encarga de obtener un producto por su id.
    *
    * @author Igirod
    * @version 1.0.0
    * @param id
    * @return ProductDTO
    * @throws ProductNotFoundException
    */
   public ProductDTO getProductById(Long id) throws ProductNotFoundException;

   /**
    * Este método se encarga de obtener todos los productos con un
    * paginado de diez productos por página.
    *
    * @author Igirod
    * @version 1.0.1
    * @param page
    * @param merchantId
    * @param status     Optional
    * @return List<ProductDTO>
    */
   public List<ProductDTO> getProductsPaginated(Integer page, Long merchantId, Optional<Integer> status);

   /**
    * Este método se encarga de obtener todos los productos segun
    * el tipo de filtrado con un paginado de diez productos por página.
    *
    * @author Igirod0
    * @version 1.0.1
    * @param page
    * @param typeFilter
    * @return List<ProductDTO>
    */
   public List<ProductDTO> getProductsByTypeFilterPaginated(Integer page, TypeFilter typeFilter);

   /**
    * Este método tiene la obligación de actualizar el frontPage de un producto.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param valueOf
    * @param image
    * @return Integer
    */
   @Modifying
   public String updateFrontPage(Long productId, MultipartFile image);

   /**
    * Este método tiene la obligación de actualizar un producto.
    *
    * @author Igirod0
    * @version 1.0.2
    * @param productId
    * @param title
    * @param price
    * @param stock
    * @param status
    * @param typeOfSale
    * @param manufacturingTime
    * @param typeOfPrice
    * @return Integer
    */
   public Integer updateProduct(Long productId, Optional<String> title, Optional<Double> price, Optional<Integer> stock,
         Optional<Integer> status, Optional<String> typeOfSale);

   /**
    * Este método tiene la obligación de eliminar un producto.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param productId
    * @return Integer
    */
   public Integer deleteProduct(Long productId);

   /**
    * Este método tiene la obligación de crear un producto.
    * 
    *
    * @author Igirod0
    * @version 1.0.2
    * @param frontPage
    * @param userId
    * @param productId
    * @param title
    * @param typeOfSale
    * @param price
    * @param stock
    * @param status
    * @param manufacturingTime
    * @param invenstmentNote
    * @param invenstmentAmount
    * @param invenstmentTitle
    * @param manufacturingType
    * @param segmentTitle
    * @param segmentDescription
    * @param segmentMedia
    * @param hashtagValue
    * @param keywordValue
    * @param sellerQuestionValue
    * @param sellerQuestionType
    * @param sellerQuestionLimit
    * @param sellerQuestionRequired
    * @param typeOfPrice
    * @param sellerQuestionOptions
    * @return Long
    */
   public ProductDTO createProduct(Optional<MultipartFile> frontPage, Optional<String> title,
         Optional<String> typeOfSale, Optional<String> price, Optional<String> stock, Optional<String> status,
         Optional<String> userId, Optional<String> manufacturingTime, Optional<String> invenstmentNote,
         Optional<String> invenstmentAmount, Optional<String> invenstmentTitle, Optional<String> manufacturingType,
         Optional<String> segmentTitle, Optional<String> segmentDescription, Optional<MultipartFile> segmentMedia,
         Optional<String> hashtagValue, Optional<List<String>> keywordValue, Optional<String> sellerQuestionValue,
         Optional<String> sellerQuestionType, Optional<String> sellerQuestionLimit,
         Optional<String> sellerQuestionRequired, Optional<String> typeOfPrice,
         Optional<List<String>> sellerQuestionOptions);

   public Long createProduct(Optional<Long> userId, Optional<Long> productId, String title, String tStock, Double price,
         Integer stock);

   /**
    * Método que tiene la obligación de asociar un producto a un comerciante.
    *
    * @author Igirod0
    * @version 1.0.1
    * @param productId
    * @param userId
    * @return Long
    */
   public Long updateAdminSellAssociation(Long productId, Long userId);

   /**
    * Método que tiene la obligación de obtener un producto por su id.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param productId
    * @return ArticleDTO
    */
   public ArticleDTO getAdminSellProduct(Long productId);

   /**
    * Método que tiene la obligación de actualizar las medias de un producto.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param productId
    * @param index
    * @param title
    * @param price
    * @param tPrice
    * @param stock
    * @param tStock
    * @param existImages
    * @param newImages
    * @return List<ArticleImageDTO>
    */
   public List<ArticleImageDTO> updateAdminSellProductMedia(Long productId, String index, Optional<String> title,
         Optional<String> price, Optional<String> tPrice, Optional<String> stock, Optional<String> tStock,
         List<String> existImages, List<MultipartFile> newImages);
}
