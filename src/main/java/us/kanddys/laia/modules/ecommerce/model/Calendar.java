package us.kanddys.laia.modules.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.model.Utils.TypeCalendar;

/**
 * Esta clase representa un calendario.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
@Entity
@Table(name = "calendars")
public class Calendar {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;
   @Column(name = "merchant_id")
   private Long merchantId;
   @Column(name = "delay")
   private Integer delay;
   @Enumerated(EnumType.STRING)
   @Column(name = "type")
   private TypeCalendar type;

   public Calendar() {} 
}
