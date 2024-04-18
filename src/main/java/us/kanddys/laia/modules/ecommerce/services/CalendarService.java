package us.kanddys.laia.modules.ecommerce.services;

import us.kanddys.laia.modules.ecommerce.controller.dto.CalendarDTO;

/**
 * Esta clase contiene las obligaciones que debe implementar la clase
 * CalendarServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public interface CalendarService {

   /**
    * Este método devuelve un calendario segun el merchantId pasado por parametro.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param day
    * @param month
    * @param year
    * @param calendarId
    * @return CalendarDTO
    */
   public CalendarDTO getCalendarByMerchantId(Integer year, Integer month, Integer day,
         Long calendarId);
}
