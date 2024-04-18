package us.kanddys.laia.modules.ecommerce.services;

import java.util.Optional;

import us.kanddys.laia.modules.ecommerce.controller.dto.BuyerDTO;

/**
 * Esta interface contiene las obligaciones que debe implementar la
 * BuyerServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public interface BuyerService {

   /**
    * Este método se encarga de obtener el comprador por su id.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param id
    * @return BuyerDTO
    */
   public BuyerDTO getBuyerById(Long id);

   /**
    * Este método se encarga de crear un comprador.
    * 
    * @param merchantId
    * @param email
    * @param name
    * @param surname
    * @param phone
    * @param count
    * @param media
    * @return Long
    */
   public Long cBuyer(Long merchantId, String email, String name, String surname, String phone, Integer count,
         String media);

   /**
    * Este método se encarga de actualizar un comprador.
    *
    * @param buyerId
    * @param email
    * @param name
    * @param surname
    * @param phone
    * @param count
    * @param media
    * @return Integer
    */
   public Integer uBuyer(Long buyerId, Optional<String> email, Optional<String> name, Optional<String> surname,
         Optional<String> phone, Optional<Integer> count, Optional<String> media);

   /**
    * Este método se encarga de eliminar un comprador.
    *
    * @param buyerId
    * @return Integer
    */
   public Integer dBuyer(Long buyerId);
}
