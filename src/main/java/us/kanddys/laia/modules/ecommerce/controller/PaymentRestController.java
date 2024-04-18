package us.kanddys.laia.modules.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import us.kanddys.laia.modules.ecommerce.services.payment.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/payments")
public class PaymentRestController {

   @Autowired
   private PaymentService paymentService;

   @GetMapping("/paypal")
   public String getPayPalToken() {
      return paymentService.createPayPalPayment();
   }
}
