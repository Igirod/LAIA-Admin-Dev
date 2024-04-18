package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import us.kanddys.laia.modules.ecommerce.controller.dto.AddressDTO;
import us.kanddys.laia.modules.ecommerce.exception.AddressNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.model.Address;
import us.kanddys.laia.modules.ecommerce.repository.AddressCriteriaRepository;
import us.kanddys.laia.modules.ecommerce.repository.AddressJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.AddressService;

/**
 * Esta clase implementa las obligaciones de la interface AddressService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class AddressServiceImpl implements AddressService {

   @Autowired
   private AddressCriteriaRepository addressCriteriaRepository;

   @Autowired
   private AddressJpaRepository addressJpaRepository;

   @Override
   public List<AddressDTO> findAddressByUserId(Integer page, Long userId) {
      return addressCriteriaRepository.findAddressPaginated(page, userId).stream().map(AddressDTO::new)
            .collect(Collectors.toList());
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer deleteAddress(Long addressId) {
      return addressJpaRepository.deleteAddress(addressId);
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer updateAddress(Long id, Optional<Long> userId, Optional<String> title, Optional<String> lng,
         Optional<String> lat, Optional<String> direction) {
      var address = addressJpaRepository.findById(id);
      if (address.isEmpty())
         throw new AddressNotFoundException(ExceptionMessage.ADDRESS_NOT_FOUND);
      else {
         var addressToUpdate = address.get();
         userId.ifPresent(addressToUpdate::setUserId);
         title.ifPresent(addressToUpdate::setTitle);
         lng.ifPresent(addressToUpdate::setLng);
         lat.ifPresent(addressToUpdate::setLat);
         direction.ifPresent(addressToUpdate::setDirection);
         addressJpaRepository.save(addressToUpdate);
         return 1;
      }
   }

   @Override
   public Long addAddress(Long userId, Optional<String> title, Optional<String> lng, Optional<String> lat,
         Optional<String> direction) {
      return addressJpaRepository.save(new Address(userId, null, title, lng, lat, direction)).getId();
   }

}
