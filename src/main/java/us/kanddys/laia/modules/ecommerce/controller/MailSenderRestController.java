package us.kanddys.laia.modules.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import us.kanddys.laia.modules.ecommerce.controller.dto.MailDTO;
import us.kanddys.laia.modules.ecommerce.services.MailSenderService;

@RestController
@RequestMapping("/mail")
@Tag(name = "Mail Sender Rest Controller", description = "Operaiones REST relacionadas al envio de correos.")
public class MailSenderRestController {

   @Autowired
   private MailSenderService mailSenderService;

   @Operation(description = "Servicio que tiene la obligación de enviar un correo para cambiar la contraseña.")
   @Parameter(name = "mailDTO", description = "Data transfer object de MailDTO", required = true)
   @ApiResponse(responseCode = "200", description = "Devuelve un mensaje de éxito.")
   @PostMapping("/send/change-password")
   public ResponseEntity<String> sendEmailChangePassword(@RequestBody MailDTO mailDTO) throws MessagingException {
      mailSenderService.sendEmailChangePassword(mailDTO);
      return ResponseEntity.ok("Mail sent successfully!");
   }

   @Operation(description = "Servicio que tiene la obligación de enviar un correo con la factura de la orden.")
   @Parameter(name = "mailDTO", description = "Data transfer object de MailDTO", required = true)
   @ApiResponse(responseCode = "200", description = "Devuelve un mensaje de éxito.")
   @PostMapping("/send/user-invoice")
   public ResponseEntity<String> sendInvoiceOrder(@RequestBody MailDTO mailDTO) throws MessagingException {
      mailSenderService.sendUserOrder(mailDTO);
      return ResponseEntity.ok("Mail sent successfully!");
   }
}
