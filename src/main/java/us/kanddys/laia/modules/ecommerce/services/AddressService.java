package us.kanddys.laia.modules.ecommerce.services;

import java.util.List;
import java.util.Optional;

import us.kanddys.laia.modules.ecommerce.controller.dto.AddressDTO;

/**
 * Esta interface contiene las obligacione que debe implementar la clase
 * AddressServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public interface AddressService {

   /**
    * Este método se encarga de obtener la dirección de un usuario.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param userId
    * @param page
    * @return List<AddressDTO>
    */
   public List<AddressDTO> findAddressByUserId(Integer page, Long userId);

   /**
    * Este método se encarga de eliminar una dirección.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param addressId
    * @return
    */
   public Integer deleteAddress(Long addressId);

   /**
    * Este método se encarga de actualizar una dirección.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param id
    * @param userId
    * @param title
    * @param lng
    * @param lat
    * @param direction
    * @return Integer
    */
   public Integer updateAddress(Long id, Optional<Long> userId, Optional<String> title, Optional<String> lng,
         Optional<String> lat, Optional<String> direction);

   /**
    * Este método se encarga de agregar una dirección.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param id
    * @param userId
    * @param title
    * @param lng
    * @param lat
    * @param direction
    * @return
    */
   public Long addAddress(Long userId, Optional<String> title, Optional<String> lng,
         Optional<String> lat, Optional<String> direction);

}
