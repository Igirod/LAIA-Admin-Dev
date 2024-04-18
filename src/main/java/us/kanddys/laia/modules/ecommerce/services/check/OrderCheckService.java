package us.kanddys.laia.modules.ecommerce.services.check;

/**
 * Esta interface contiene las obligaciones que debe implementar la
 * clase InvoiceCheckServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public interface OrderCheckService {

   /**
    * Este método verifica si el id del comerciante existe.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param merchantId
    * @return true si existe y false si no.
    */
   public Boolean existsMerchantId(Long merchantId);

   /**
    * Este método verifica si el id del carro de compras existe.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param invoiceId
    * @return true si existe y false si no.
    */
   public Boolean existOrderId(Long invoiceId);

   /**
    * Este método verifica si el id de pago existe.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param paymentId
    * @return true si existe y false si no.
    */
   public Boolean existsPaymentId(Long paymentId);

   /**
    * Este método actualiza el total de la orden.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param invoiceId
    * @param total
    */
   public void updateTotal(Long invoiceId, Double total);
}
