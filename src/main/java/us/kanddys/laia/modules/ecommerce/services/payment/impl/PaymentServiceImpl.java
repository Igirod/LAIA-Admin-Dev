package us.kanddys.laia.modules.ecommerce.services.payment.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.modules.ecommerce.controller.dto.PaymentDTO;
import us.kanddys.laia.modules.ecommerce.repository.PaymentCriteriaRepository;
import us.kanddys.laia.modules.ecommerce.services.payment.PaymentService;
import us.kanddys.laia.modules.ecommerce.services.payment.method.PayPalService;

@Service
public class PaymentServiceImpl implements PaymentService {

   @Autowired
   private PaymentCriteriaRepository paymentCriteriaRepository;

   @Autowired
   private PayPalService payPalService;

   @Override
   public List<PaymentDTO> findPaymentsPaginated(Integer page, Optional<Long> merchantId, Optional<Integer> status) {
      return paymentCriteriaRepository.findPaymentsPaginated(page, merchantId, status).stream().map(PaymentDTO::new)
            .collect(Collectors.toList());
   }

   @Override
   public String createPayPalPayment() {
      return payPalService.getToken();
   }
}
