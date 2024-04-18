package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.model.Address;

/**
 * Esta clase representa un data transfer object (DTO) de la dirección de un usuario.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
public class AddressDTO {
   @JsonProperty
   private Long id;
   @JsonProperty
   private Long userId;
   @JsonProperty
   private String title;
   @JsonProperty
   private String lng;
   @JsonProperty
   private String lat;
   @JsonProperty
   private String direction;

   public AddressDTO() {}

   /**
    * Constructor personalizado utilizado en diferentes servicios.
    *
    * @author Igirod0
    * @version 1.0.0
    */
   public AddressDTO(Address address) {
      super();
      this.id = (address.getId() != null) ? address.getId() : null;
      this.userId = (address.getUserId() != null) ? address.getUserId() : null;
      this.title = (address.getTitle() != null) ? address.getTitle() : null;
      this.lng = (address.getLng() != null) ? address.getLng() : null;  
      this.lat = (address.getLat() != null) ? address.getLat() : null;
      this.direction = (address.getDirection() != null) ? address.getDirection() : null;
   }
}
