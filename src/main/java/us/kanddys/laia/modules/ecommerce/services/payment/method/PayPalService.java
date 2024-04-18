package us.kanddys.laia.modules.ecommerce.services.payment.method;

/**
 * Esta interface contiene las olbigaciones que debe implementar la clase
 * PayPalServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public interface PayPalService {

   /**
    * Método que tiene la obligación de obtener el token asociado al negocio.
    *
    * @author Igirod0
    * @version 1.0.0
    * @return token asociado al negocio.
    */
   public String getToken();
}
