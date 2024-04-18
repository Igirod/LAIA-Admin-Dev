package us.kanddys.laia.modules.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase contiene los atributos y métodos necesarios para representar una
 * condición asociada a una tabla.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
@Entity
@Table(name = "table_conditions")
public class TableCondition {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;
   @Column(name = "library_id")
   private Long libraryId;
   @Column(name = "type_condition")
   private String typeCondition;
   @Column(name = "alias")
   private String alias;

   public TableCondition() {
   }
}
