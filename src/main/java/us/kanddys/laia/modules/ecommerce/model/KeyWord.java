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
 * Esta clase contiene las palabras claves que se utilizarán para la busqueda de
 * productos.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
@Table(name = "key_words")
@Entity
public class KeyWord {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;
   @Column(name = "word")
   private String word;
   @Column(name = "user_id")
   private Long userId;

   public KeyWord() {
   }

   public KeyWord(String word, Long userId) {
      super();
      this.word = word;
      this.userId = userId;
   }
}
