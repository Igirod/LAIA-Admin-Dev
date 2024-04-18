package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.modules.ecommerce.controller.dto.BuyerDTO;
import us.kanddys.laia.modules.ecommerce.exception.BuyerNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.model.Buyer;
import us.kanddys.laia.modules.ecommerce.repository.BuyerJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.BuyerService;

/**
 * Esta clase implementa las obligaciones de la interface BuyerService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class BuyerServiceImpl implements BuyerService {

   @Autowired
   private BuyerJpaRepository buyerJpaRepository;

   @Override
   public BuyerDTO getBuyerById(Long id) {
      Optional<Buyer> buyer = buyerJpaRepository.findById(id);
      if (buyer.isEmpty()) {
         return new BuyerDTO(null, 0);
      } else {
         return new BuyerDTO(buyer.get(), 1);
      }
   }

   @Override
   public Long cBuyer(Long merchantId, String email, String name, String surname, String phone, Integer count,
         String media) {
      Buyer buyer = new Buyer();
      buyer.setId(null);
      buyer.setMerchantId((merchantId != null) ? merchantId : null);
      buyer.setEmail((email != null) ? email : null);
      buyer.setName((name != null) ? name : null);
      buyer.setSurname((surname != null) ? surname : null);
      buyer.setPhone((phone != null) ? phone : null);
      buyer.setCount((count != null) ? count : null);
      buyer.setMedia((media != null) ? media : null);
      buyerJpaRepository.save(buyer);
      return buyer.getId();
   }

   @Override
   public Integer uBuyer(Long buyerId, Optional<String> email, Optional<String> name, Optional<String> surname,
         Optional<String> phone, Optional<Integer> count, Optional<String> media) {
      Optional<Buyer> buyer = buyerJpaRepository.findById(buyerId);
      if (buyer.isEmpty()) {
         throw new BuyerNotFoundException(ExceptionMessage.BUYER_NOT_FOUND);
      }
      Buyer buyerToUpdate = buyer.get();
      email.ifPresent(buyerToUpdate::setEmail);
      name.ifPresent(buyerToUpdate::setName);
      surname.ifPresent(buyerToUpdate::setSurname);
      phone.ifPresent(buyerToUpdate::setPhone);
      count.ifPresent(buyerToUpdate::setCount);
      media.ifPresent(buyerToUpdate::setMedia);
      buyerJpaRepository.save(buyerToUpdate);
      return 1;
   }

   @Override
   public Integer dBuyer(Long buyerId) {
      Optional<Buyer> buyer = buyerJpaRepository.findById(buyerId);
      if (buyer.isEmpty()) {
         throw new BuyerNotFoundException(ExceptionMessage.BUYER_NOT_FOUND);
      }
      buyerJpaRepository.deleteById(buyer.get().getId());
      return 1;
   }

}
