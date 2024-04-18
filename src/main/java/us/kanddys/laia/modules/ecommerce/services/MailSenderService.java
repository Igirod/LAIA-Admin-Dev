package us.kanddys.laia.modules.ecommerce.services;

import jakarta.mail.MessagingException;
import us.kanddys.laia.modules.ecommerce.controller.dto.MailDTO;

/**
 * Esta interfaz contiene las obligaciones que debe implementar la clase
 * MailSenderServiceImpl.
 * 
 * @autor Igirod0
 * @version 1.0.0
 */
public interface MailSenderService {

   /**
    * Este método se encarga de enviar un correo electrónico al usuario con el
    * enlace para cambiar la contraseña.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param mailDTO
    * @throws MessagingException
    */
   public void sendEmailChangePassword(MailDTO mailDTO) throws MessagingException;

   /**
    * Este método se encarga de enviar un correo electrónico al usuario con el
    * detalle de la factura que compro.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param mailDTO
    * @throws MessagingException
    */
   public void sendUserOrder(MailDTO mailDTO) throws MessagingException;

}
