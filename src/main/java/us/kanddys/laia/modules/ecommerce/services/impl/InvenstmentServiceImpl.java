package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.modules.ecommerce.controller.dto.InvenstmentDTO;
import us.kanddys.laia.modules.ecommerce.exception.InvenstmentNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.model.Invenstment;
import us.kanddys.laia.modules.ecommerce.repository.InvenstmentJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.InvenstmentService;

/**
 * Esta clase implementa las obligaciones de la interfaz InventsmentService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class InvenstmentServiceImpl implements InvenstmentService {

   @Autowired
   private InvenstmentJpaRepository invenstmentJpaRepository;

   @Override
   public Integer createInvenstment(Long productId, Optional<Double> amount, Optional<String> note,
         Optional<String> title) {
      invenstmentJpaRepository.save(new Invenstment(null, productId, (amount.isPresent() ? amount.get() : null),
            (note.isPresent() ? note.get() : null), (title.isPresent() ? title.get() : null)));
      return 1;
   }

   @Override
   public Integer deleteInvenstment(Long invenstmentId) {
      invenstmentJpaRepository.deleteById(invenstmentId);
      return 1;
   }

   @Override
   public Integer updateInvenstment(Long invenstmentId, Optional<Double> amount,
         Optional<String> note, Optional<String> title) {
      var invenstment = invenstmentJpaRepository.findById(invenstmentId);
      if (invenstment.isEmpty())
         throw new InvenstmentNotFoundException(ExceptionMessage.INVESTMENT_NOT_FOUND);
      var invenstmentToUpdate = invenstment.get();
      amount.ifPresent(invenstmentToUpdate::setAmount);
      note.ifPresent(invenstmentToUpdate::setNote);
      title.ifPresent(invenstmentToUpdate::setTitle);
      invenstmentJpaRepository.save(invenstmentToUpdate);
      return 1;
   }

   @Override
   public List<InvenstmentDTO> getAdminSellInvenstments(Long productId) {
      return invenstmentJpaRepository.findByProductId(productId).stream().map(InvenstmentDTO::new).toList();
   }

}
