package us.kanddys.laia.modules.ecommerce.services;

import java.util.List;

import us.kanddys.laia.modules.ecommerce.controller.dto.MemoryDTO;

/**
 * Esta interface contiene las obligaciones que debe implementar la clase
 * de MemoryServiceImpl.
 * 
 *  @author Igirod0
 *  @version 1.0.0
 */

public interface MemoryService {
   /**
    * Este método se encarga de obtener todas las memorias.
    *
    * @author Igirod0
    * @version 1.0.0
    * @return List<MemoryDTO>
    */
   public List<MemoryDTO> getAllMemories();
}
