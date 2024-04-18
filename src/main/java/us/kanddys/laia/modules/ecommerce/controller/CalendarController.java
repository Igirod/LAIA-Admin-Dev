package us.kanddys.laia.modules.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import us.kanddys.laia.modules.ecommerce.controller.dto.CalendarDTO;
import us.kanddys.laia.modules.ecommerce.services.CalendarService;

@Controller
public class CalendarController {

   @Autowired
   private CalendarService calendarService;

   @QueryMapping("gCalendar")
   public CalendarDTO getCalendar(@Argument Integer year, @Argument Integer month,
         @Argument Integer day, @Argument Long merchantId) {
      return calendarService.getCalendarByMerchantId(year, month, day, merchantId);
   }
}
