package us.kanddys.laia.modules.ecommerce.services;

import java.util.List;
import java.util.Optional;

import us.kanddys.laia.modules.ecommerce.controller.dto.BatchDTO;

/**
 * Esta clase contiene las obligaciones que debe implementar BatchServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public interface BatchService {

   /**
    * Este método devuelve los batches agregados por el merchantId.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param calendarId
    * @param day
    * @param exceptionalDate
    * @return List<BatchDTO>
    */
   public List<BatchDTO> getBatchesByCalendarId(Long calendarId, String day, String date,
         Optional<Integer> exceptionalDate);

   /**
    * Este método crea un batch.
    *
    * @param calendarId
    * @param days
    * @param date
    * @param fromTime
    * @param toTime
    * @param maxLimit
    * @param title
    * @return Integer
    */
   public Integer createBatch(Long calendarId, Optional<Integer> days, Optional<String> date, Optional<String> fromTime,
         Optional<String> toTime, Optional<Integer> maxLimit, Optional<String> title);

   /**
    * Este método actualiza un batch.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param batchId
    * @param days
    * @param date
    * @param fromTime
    * @param toTime
    * @param maxLimit
    * @param title
    * @return Integer
    */
   public Integer updateBatch(Long batchId, Optional<Integer> days, Optional<String> date, Optional<String> fromTime,
         Optional<String> toTime, Optional<Integer> maxLimit, Optional<String> title);

   /**
    * 
    * Este método elimina un batch.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param batchId
    * @return Integer
    */
   public Integer deleteBatch(Long batchId);
}