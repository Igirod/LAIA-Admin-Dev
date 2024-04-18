package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.model.Memory;

@Data
@AllArgsConstructor
public class MemoryDTO {
   @JsonProperty
   private Long id;
   @JsonProperty
   private String memory;
   @JsonProperty
   private Long merchantId;

   public MemoryDTO() {
   }

   /**
    * Constructor personalizado utilizado en diferentes servicios.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param memory
    */
   public MemoryDTO(Memory memory) {
      super();
      this.id = (memory.getId() != null) ? memory.getId() : null;
      this.memory = (memory.getMemory() != null) ? memory.getMemory() : null;
      this.merchantId = (memory.getMerchantId() != null) ? memory.getMerchantId() : null;
   }
}
