package us.kanddys.laia.modules.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import us.kanddys.laia.modules.ecommerce.controller.dto.BatchDTO;
import us.kanddys.laia.modules.ecommerce.services.BatchService;

@Controller
public class BatchController {

   @Autowired
   private BatchService batchService;

   @QueryMapping("gBatches")
   public List<BatchDTO> getBatchesByCalendarId(@Argument Long calendarId, @Argument String day, @Argument String date,
         @Argument Optional<Integer> exceptionalDate) {
      return batchService.getBatchesByCalendarId(calendarId, day, date, exceptionalDate);
   }

   @MutationMapping("cBatch")
   public Integer createBatch(@Argument Long calendarId, @Argument Optional<Integer> days,
         @Argument Optional<String> date, @Argument Optional<String> fromTime, @Argument Optional<String> toTime,
         @Argument Optional<Integer> maxLimit, @Argument Optional<String> title) {
      return batchService.createBatch(calendarId, days, date, fromTime, toTime, maxLimit, title);
   }

   @MutationMapping("uBatch")
   public Integer updateBatch(@Argument Long batchId, @Argument Optional<Integer> days, @Argument Optional<String> date,
         @Argument Optional<String> fromTime, @Argument Optional<String> toTime, @Argument Optional<Integer> maxLimit,
         @Argument Optional<String> title) {
      return batchService.updateBatch(batchId, days, date, fromTime, toTime, maxLimit, title);
   }

   @MutationMapping("dBatch")
   public Integer deleteBatch(@Argument Long batchId) {
      return batchService.deleteBatch(batchId);
   }
}
