package us.kanddys.laia.modules.ecommerce.services;

/**
 * Esta interface contiene las obligaciones que debe implementar la clase
 * CategoryProductServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public interface CategoryProductService {

   /**
    * Este método se encarga de crear una nueva relación entre una categoría y un
    * producto.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param categoryId El id de la
    * @param productId  El id del producto.
    */
   public Integer createCategoryProduct(Long categoryId, Long productId);

   /**
    * Este método se encarga de eliminar una relación entre una categoría y un
    * producto.
    * 
    * @param categoryId El id de la categoría.
    * @param productId  El id del producto.
    * @return Integer
    */
   public Integer deleteCategoryProduct(Long categoryId, Long productId);
}
