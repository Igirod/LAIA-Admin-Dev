package us.kanddys.laia.modules.ecommerce.services;

import java.util.Map;

import us.kanddys.laia.modules.ecommerce.controller.dto.LibraryConfigDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.LibraryMiniatureDTO;

/**
 * Esta interface contiene las obligaciones quede implementar la clase
 * LibrearyServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.2
 */
public interface LibraryService {

   /**
    * Este metodo se encarga de obtener una tipo de biblioteca perteneciente a un
    * usuario.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param userId
    * @param libraryId
    * @return Map<String, Object>
    */
   public Map<String, Object> gAdminSellLibrary(Long userId, Long libraryId);

   /**
    * Este metodo se encarga de renombrar una biblioteca.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param libraryId
    * @param title
    * @param userId
    * @return Integer
    */
   public Integer uLibraryRename(Long libraryId, String title, Long userId);

   /**
    * Este metodo se encarga de cambiar la miniatura de una biblioteca.
    * 
    * @author Igirod0
    * @version 1.0.3
    * @param libraryId
    * @param miniatureHeader
    * @param miniatureTitle
    * @param miniatureSubtitle
    * @return Integer
    */
   public Integer uLibraryMiniatures(Long libraryId, String miniatureHeader, String miniatureTitle,
         String miniatureSubtitle);

   /**
    * Este metodo se encarga de obtener la configuracion de una biblioteca.
    *
    * @author Igirod0
    * @version 1.0.1
    * @param libraryId
    * @param userId
    * @return LibraryConfigDTO
    */
   public LibraryConfigDTO gLibraryConf(Long libraryId, Long userId);

   /**
    * Este metodo se encarga de obtener la miniatura de una biblioteca.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param libraryId
    * @param userId
    * @return LibraryMiniatureDTO
    */
   public LibraryMiniatureDTO gLibraryMiniatures(Long libraryId, Long userId);
}
