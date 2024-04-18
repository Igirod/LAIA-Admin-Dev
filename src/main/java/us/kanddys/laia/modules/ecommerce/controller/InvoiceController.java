package us.kanddys.laia.modules.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import us.kanddys.laia.modules.ecommerce.controller.dto.CompleteInvoiceDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceReservationDTO;
import us.kanddys.laia.modules.ecommerce.services.InvoiceReservationService;
import us.kanddys.laia.modules.ecommerce.services.InvoiceService;

@Controller
public class InvoiceController {

   @Autowired
   private InvoiceService invoiceService;

   @Autowired
   private InvoiceReservationService invoiceReservationService;

   @MutationMapping("uInvoiceN")
   public Integer updateInvoiceNote(@Argument Long invoiceId, @Argument String note) {
      return invoiceService.updateInvoiceNote(invoiceId, note);
   }

   @MutationMapping("uInvoiceS")
   public Integer updateInvoiceStatus(@Argument Long invoiceId, @Argument String status) {
      return invoiceService.updateInvoiceStatus(invoiceId, status);
   }

   @QueryMapping("gInvoice")
   public InvoiceDTO getOrder(@Argument Long orderId) {
      return invoiceService.getInvoiceById(orderId);
   }

   @QueryMapping("gAdminSellInvoiceReservation")
   public InvoiceReservationDTO getAdminSellInvoiceReservation(@Argument Long merchantId,
         @Argument List<Long> articlesIds) {
      return invoiceReservationService.getInvoiceReservation(merchantId, articlesIds);
   }

   @QueryMapping("gAdminSellInvoice")
   public CompleteInvoiceDTO getAdminSellInvoice(@Argument Long user, @Argument Long invoice) {
      return invoiceService.gAdminSellInvoice(invoice, user);
   }
}
