package us.kanddys.laia.modules.ecommerce.model;

import java.sql.Time;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase representa una excepción de fecha.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
@Entity
@Table(name = "date_exceptions")
public class ExceptionDate {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;
   @Column(name = "date")
   private Date date;
   @Column(name = "from")
   private Time from;
   @Column(name = "to")
   private Time to;
   @Column(name = "max_limit")
   private Integer limit;
   @Column(name = "title")
   private String title;

   public ExceptionDate() {}
}
