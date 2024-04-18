package us.kanddys.laia.modules.ecommerce.model.Utils;

import java.sql.Time;

/**
 * Esta clase contiene metodos para manejar horas de diferentes fechas.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public class TimeUtils {

   /**
    * Este metodo convierte una cadena de texto en una hora.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param time
    * @return Time
    */
   public static Time convertStringToTime(String time) {
      return Time.valueOf(time);
   }
}
