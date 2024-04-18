package us.kanddys.laia.modules.ecommerce.services.check.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import us.kanddys.laia.modules.ecommerce.exception.ProductCheckStockLimitedException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.repository.ProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.check.ProductCheckStockService;

/**
 * Esta clase implementa las obligaciones de la interface
 * ProductCheckStockService.
 * 
 * @author Igirod0
 * @version 1.0.1
 */
@Service
public class ProductCheckStockServiceImpl implements ProductCheckStockService {

   @Autowired
   private ProductJpaRepository productJpaRepository;

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public boolean checkStock(Long productId, Integer stock, Integer quantity) {
      switch (stock) {
         case 0:
            desactivateProduct(productId);
            return false;
         case -1:
            return true;
         default:
            if (stock >= quantity)
               updateStockByProductQuantity(productId, stock, quantity);
            return true;
      }
   }

   /**
    * Método privado que desactiva el status del producto cuando se detecta
    * que el stock es 0.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param productId
    */
   private void desactivateProduct(Long productId) {
      productJpaRepository.updateStatusByProductId(productId, 0);
   }

   @Override
   public void updateStockByProductQuantity(Long productId, Integer stock, Integer quantity) {
      if (stock >= quantity) {
         var resultStock = stock - quantity;
         if (resultStock == 0) {
            productJpaRepository.updateStockByProductId(productId, resultStock);
            desactivateProduct(productId);
         } else
            productJpaRepository.updateStockByProductId(productId, resultStock);
      } else {
         throw new ProductCheckStockLimitedException(ExceptionMessage.PRODUCT_CHECK_STOCK_LIMITED);
      }
   }

}
