package us.kanddys.laia.modules.ecommerce.services;

import java.util.List;

import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceReservationDTO;

public interface InvoiceReservationService {

   /**
    * Este método se encarga de obtener la fecha de envío de los artículos.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param merchantId
    * @param articlesIds
    * @return InvoiceReservationDTO
    */
   public InvoiceReservationDTO getInvoiceReservation(Long merchantId, List<Long> articlesIds);
}
