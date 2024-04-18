package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import us.kanddys.laia.modules.ecommerce.controller.dto.OrderProductDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.OrderProductInputDTO;
import us.kanddys.laia.modules.ecommerce.exception.OrderNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.ProductNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.model.OrderProduct;
import us.kanddys.laia.modules.ecommerce.model.OrderProductId;
import us.kanddys.laia.modules.ecommerce.model.Product;
import us.kanddys.laia.modules.ecommerce.repository.OrderJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.OrderProductCriteriaRepository;
import us.kanddys.laia.modules.ecommerce.repository.OrderProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.ProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.OrderProductService;
import us.kanddys.laia.modules.ecommerce.services.check.OrderCheckService;

/**
 * Esta clase implementa las obligaciones de la interface
 * InvoiceProductService.
 * 
 * @author Igirod0
 * @version 1.0.1
 */
@Service
public class OrderProductImpl implements OrderProductService {

   @Autowired
   private OrderJpaRepository orderJpaRepository;

   @Autowired
   private OrderProductJpaRepository orderProductJpaRepository;

   @Autowired
   private OrderProductCriteriaRepository orderProductCriteriaQueryRepository;

   @Autowired
   private OrderCheckService invoiceCheckService;

   @Autowired
   private ProductJpaRepository productJpaRepository;

   @Override
   public Integer addOrderProduct(Long orderId, Long productId) {
      if (orderProductJpaRepository.findById(new OrderProductId(orderId, productId)).isPresent()) {
         return 1;
      } else {
         if (orderJpaRepository.existByOrderId(orderId) == null)
            throw new OrderNotFoundException(ExceptionMessage.ORDER_NOT_FOUND);
         Optional<Product> product = productJpaRepository.findById(productId);
         if (product.isEmpty())
            throw new ProductNotFoundException(ExceptionMessage.PRODUCT_NOT_FOUND);
         orderProductJpaRepository.save(new OrderProduct(productId, orderId, product.get()));
         invoiceCheckService.updateTotal(orderId,
               orderProductJpaRepository.findTotalByOrderId(orderId) + orderProductJpaRepository
                     .findById(new OrderProductId(productId, orderId)).get().getProduct().getPrice() * 1);
         return 1;
      }
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer updateOrderProduct(Long orderId, List<OrderProductInputDTO> listOrderProducts) {
      var listOrderProductsInputIds = listOrderProducts.stream().map(OrderProductInputDTO::getProductId).toList();
      orderProductJpaRepository.findAllProductsIdByOrderId(orderId).stream()
            .filter(productId -> !listOrderProductsInputIds.contains(productId))
            .forEach(productId -> orderProductJpaRepository.deleteById(new OrderProductId(productId, orderId)));
      listOrderProducts.stream()
            .forEach(invoiceInputDTO -> orderProductJpaRepository.updateQuantityByOrderIdAndProductId(
                  orderId,
                  invoiceInputDTO.getProductId(),
                  invoiceInputDTO.getQuantity()));
      invoiceCheckService.updateTotal(orderId,
            orderProductJpaRepository.findAllById(listOrderProductsInputIds.stream()
                  .map(invoiceProductInputId -> new OrderProductId(invoiceProductInputId, orderId))
                  .toList()).stream()
                  .mapToDouble(orderProduct -> orderProduct.getProduct().getPrice() * orderProduct.getQuantity())
                  .sum());
      return 1;
   }

   @Override
   public List<OrderProductDTO> findInvoiceProductsByInvoiceId(Long orderId, Integer page,
         Optional<Integer> limit) {
      // return
      // orderProductCriteriaQueryRepository.findOrderProductsByOrderId(orderId, page,
      // limit).stream()
      // .map(OrderProductDTO::new).toList();
      return null;
   }
}
