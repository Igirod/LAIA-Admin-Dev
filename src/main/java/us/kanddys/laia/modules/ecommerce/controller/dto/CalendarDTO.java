package us.kanddys.laia.modules.ecommerce.controller.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase representa un data transfer object (DTO) de calendario.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
public class CalendarDTO {
   @JsonProperty
   private Long calendarId;
   @JsonProperty
   private Integer delay;
   @JsonProperty
   private String typeDelay;
   @JsonProperty
   private List<String> disabledDates;
   @JsonProperty
   private List<String> workingDays;
   @JsonProperty
   private List<String> exceptionsDates;

   public CalendarDTO() {}
}
