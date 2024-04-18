package us.kanddys.laia.modules.ecommerce.model;

import java.util.Optional;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase representa la dirección de un usuario.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Entity
@Table(name = "address_users")
@AllArgsConstructor
@Data
public class Address {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;
   @Column(name = "user_id")
   private Long userId;
   @Column(name = "title")
   private String title;
   @Column(name = "lng")
   private String lng;
   @Column(name = "lat")
   private String lat;
   @Column(name = "direction")
   private String direction;

   public Address() {
   }

   /**
    * Constructor personalizado utilizado en diferentes servicios.
    *
    * @param userId
    * @param title
    * @param userId
    * @param title
    * @param lng
    * @param lat
    * @param direction
    */
   public Address(Long userId, Long id, Optional<String> title, Optional<String> lng, Optional<String> lat,
         Optional<String> direction) {
      super();
      this.id = (id == null) ? null : id;
      this.userId = userId;
      this.title = title.orElse(null);
      this.lng = lng.orElse(null);
      this.lat = lat.orElse(null);
      this.direction = direction.orElse(null);
   }
}
