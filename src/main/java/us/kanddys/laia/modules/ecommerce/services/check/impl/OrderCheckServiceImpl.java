package us.kanddys.laia.modules.ecommerce.services.check.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import us.kanddys.laia.modules.ecommerce.repository.MerchantJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.OrderJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.check.OrderCheckService;

/**
 * Esta clase implementa las obligaciones de la interfaz
 * InvoiceCheckService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class OrderCheckServiceImpl implements OrderCheckService {

   @Autowired
   private MerchantJpaRepository merchantJpaRepository;

   @Autowired
   private OrderJpaRepository orderJpaRepository;

   @Override
   public Boolean existsMerchantId(Long merchantId) {
      return (merchantJpaRepository.existByMerchantId(merchantId) == null) ? false : true;
   }

   @Override
   public Boolean existOrderId(Long invoiceId) {
      return (orderJpaRepository.existByOrderId(invoiceId) == null) ? false : true;
   }

   @Override
   public Boolean existsPaymentId(Long paymentId) {
      return false;
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public void updateTotal(Long invoiceId, Double total) {
      orderJpaRepository.updateTotal(invoiceId, total);
   }
}
