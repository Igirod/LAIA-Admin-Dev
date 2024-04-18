package us.kanddys.laia.modules.ecommerce.services;

import java.util.Optional;

/**
 * Esta interface contiene las obligaciones que debe implementar la clase
 * CategoryServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public interface CategoryService {

   /**
    * Este método se encarga de crear una nueva categoría.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param title El título de la categoría.
    * @return El id de la categoría creada.
    */
   public Long createCategory(String title);

   /**
    * Este método se encarga de actualizar una categoria.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param id    El id de la categoría.
    * @param title El título de la categoría.
    */
   public Integer updateCategory(Long id, Optional<String> title);

   /**
    * Este método se encarga de eliminar una categoría.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param id El id de la categoría.
    * @return Integer
    */
   public Integer deleteCategory(Long id);

   /**
    * Este método se encarga de obtener el id de una categoria por su title.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param title El título de la categoría.
    * @return El id de la categoría.
    */
   public Long getCategoryIdByTitle(String title);
}
