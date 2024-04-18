package us.kanddys.laia.modules.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoicePaymentDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceRBodyDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.utils.LongDTO;
import us.kanddys.laia.modules.ecommerce.services.InvoiceService;

@RestController
@RequestMapping("/invoices")
@Tag(name = "Invoice Rest Controller", description = "Operaciones REST relacionadas a las facturas.")
public class InvoiceRestController {

   @Autowired
   private InvoiceService invoiceService;

   @Operation(description = "Servicio que tiene la obligación de actualizar el voucher de una orden.")
   @Parameters({
         @Parameter(name = "voucher", description = "Voucher de la orden", required = true, example = "voucher"),
         @Parameter(name = "orderId", description = "Identificador de la orden", required = true, example = "1") })
   @ApiResponse(responseCode = "200", description = "Devuelve un data transfer object de OrderPaymentDTO.")
   @RequestMapping(method = { RequestMethod.PUT }, value = "/update-voucher", produces = {
         "application/json" }, consumes = { "multipart/form-data" })
   public InvoicePaymentDTO updateOrderVoucher(@RequestPart MultipartFile voucher,
         @RequestPart String invoiceId) {
      return invoiceService.updateInvoiceVoucher(voucher, Long.valueOf(invoiceId));
   }

   @Operation(description = "Servicio que tiene la obligación de crear una factura de venta.")
   @ApiResponse(responseCode = "200", description = "Devuelve el identificador de la factura creada.")
   @PostMapping(value = "/create-invoice")
   public LongDTO createAdminSellInvoice(@RequestBody InvoiceRBodyDTO invoiceRBodyDTO) {
      return invoiceService.createAdminSellInvoice(
            (invoiceRBodyDTO.getUserId() != null ? invoiceRBodyDTO.getUserId() : null),
            (invoiceRBodyDTO.getEmail() != null ? invoiceRBodyDTO.getEmail() : null),
            (invoiceRBodyDTO.getPhone() != null ? invoiceRBodyDTO.getPhone() : null),
            (invoiceRBodyDTO.getName() != null ? invoiceRBodyDTO.getName() : null),
            (invoiceRBodyDTO.getSurname() != null ? invoiceRBodyDTO.getSurname() : null),
            invoiceRBodyDTO.getMerchantId(), invoiceRBodyDTO.getArticles(),
            (invoiceRBodyDTO.getAddress() == null ? null : invoiceRBodyDTO.getAddress()),
            invoiceRBodyDTO.getBatchId(), invoiceRBodyDTO.getReservation(), invoiceRBodyDTO.getReservationType(),
            (invoiceRBodyDTO.getMessage() != null ? invoiceRBodyDTO.getMessage() : null));
   }
}
