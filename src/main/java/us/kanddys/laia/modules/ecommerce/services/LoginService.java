package us.kanddys.laia.modules.ecommerce.services;

import us.kanddys.laia.modules.ecommerce.controller.dto.RCheckEmailDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.RLoginUserDTO;

/**
 * Esta clase contiene las obligaciones que debe implementar la clase
 * LoginServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.2
 */
public interface LoginService {

   /**
    * Este método verifica si el correo se encuentra registrado.
    *
    * @author Igirod0
    * @version 1.0.2
    * @param userId
    * @param email
    * @return Map<String, Object>
    */
   public RCheckEmailDTO sqqCheck(String email);

   /**
    * Este método verifica si el email y la contraseña son correctos.
    *
    * @author Igirod0
    * @version 1.0.1
    * @param userId
    * @param password
    * @return RLoginUserDTO
    */
   public RLoginUserDTO sqqLogin(Long userId, String password);

}
