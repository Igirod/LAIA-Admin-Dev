package us.kanddys.laia.modules.ecommerce.controller.dto;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase representa un data transfer object (DTO) que se utiliza para 
 * recibir la información de una imagen de un producto.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
public class ImageProductInputDTO {
   @JsonProperty
   private MultipartFile image;
   @JsonProperty
   private Long productId;
   @JsonProperty
   private String imageName;
}
