package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.modules.ecommerce.controller.dto.ManufacturingProductDTO;
import us.kanddys.laia.modules.ecommerce.model.ManufacturingProduct;
import us.kanddys.laia.modules.ecommerce.repository.ManufacturingProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.ManufacturingProductService;

/**
 * Esta clase implementa las obligaciones de la
 * interface ManufacturingProductService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class ManufacturingProductServiceImpl implements ManufacturingProductService {

   @Autowired
   private ManufacturingProductJpaRepository manufacturingProductJpaRepository;

   @Override
   public Integer createManufacturingProduct(Long productId, Optional<String> type, Optional<Integer> time) {
      manufacturingProductJpaRepository
            .save(new ManufacturingProduct(null, productId, (type.isPresent() ? type.get() : null),
                  (time.isPresent() ? time.get() : null)));
      return 1;
   }

   @Override
   public ManufacturingProductDTO getManufacturingByProductId(Long productId) {
      return new ManufacturingProductDTO(manufacturingProductJpaRepository.findByProductId(productId));
   }

}
