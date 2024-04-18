package us.kanddys.laia.modules.ecommerce.services.impl;

import java.text.ParseException;
import java.time.YearMonth;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import us.kanddys.laia.modules.ecommerce.controller.dto.CalendarDTO;
import us.kanddys.laia.modules.ecommerce.model.Utils.CalendarDay;
import us.kanddys.laia.modules.ecommerce.model.Utils.DateUtils;
import us.kanddys.laia.modules.ecommerce.repository.BatchJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.CalendarJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.DisabledDateJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.CalendarService;

/**
 * Esta clase implementa las obligaciones de la interface CalendarService.
 * 
 * @author Igirod0
 * @version 1.0.1
 */
@Service
public class CalendarServiceImpl implements CalendarService {

   @Autowired
   private DisabledDateJpaRepository disabledDateJpaRepository;

   @Autowired
   private BatchJpaRepository batchJpaRepository;

   @Autowired
   private CalendarJpaRepository calendarJpaRepository;

   @Transactional(rollbackOn = Exception.class)
   @Override
   public CalendarDTO getCalendarByMerchantId(Integer year, Integer month, Integer day, Long merchantId) {
      try {
         var startDate = year.toString() + "-" + month.toString() + "-" + day.toString();
         var endDate = DateUtils.convertStringToDateWithoutTime(YearMonth.of(year, month).atEndOfMonth().toString());
         Map<String, Object> calendar = calendarJpaRepository.findTypeAndDelayAndCalendarIdByMerchantId(merchantId);
         var calendarId = Long.valueOf((Integer) calendar.get("id"));

         return new CalendarDTO(calendarId, (Integer) calendar.get("delay"), (String) calendar.get("type"),
               disabledDateJpaRepository.findDisabedDatesByCalendarIdRange(
                     DateUtils.convertStringToDateWithoutTime(startDate), endDate, calendarId),
               CalendarDay.getDays(batchJpaRepository.findDaysByCalendarId(calendarId)),
               batchJpaRepository.findExceptionBatchesDatesByCalendarIdAndDateNotNull(calendarId,
                     DateUtils.convertStringToDateWithoutTime(startDate), endDate));
      } catch (ParseException e) {
         throw new RuntimeException("Error al convertir la fecha");
      }
   }

}
