package us.kanddys.laia.modules.ecommerce.services;

import java.util.Optional;

import us.kanddys.laia.modules.ecommerce.controller.dto.ManufacturingProductDTO;

/**
 * @author Igirod0
 * @version 1.0.0
 */
public interface ManufacturingProductService {

   public Integer createManufacturingProduct(Long productId, Optional<String> type, Optional<Integer> time);

   public ManufacturingProductDTO getManufacturingByProductId(Long productId);
}
