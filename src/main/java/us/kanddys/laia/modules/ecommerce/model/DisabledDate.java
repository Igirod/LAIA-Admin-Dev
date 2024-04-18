package us.kanddys.laia.modules.ecommerce.model;

import java.text.ParseException;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.model.Utils.DateUtils;

/**
 * Esta clase representa una fecha deshabilitada.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
@Entity
@Table(name = "disabled_dates")
public class DisabledDate {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;
   @Column(name = "calendar_id")
   private Long calendarId;
   @Column(name = "date")
   private Date date;

   public DisabledDate() {
   }

   /**
    * Constructor de la clase.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param calendarId
    * @param date
    */
   public DisabledDate(Object[] object) {
      super();
      this.id = null;
      this.calendarId = Long.parseLong(object[0].toString());
      try {
         this.date = DateUtils.convertStringToDateWithoutTime(object[1].toString());
      } catch (ParseException e) {
         throw new RuntimeException("Error al convertir la fecha");
      }
   }
}
