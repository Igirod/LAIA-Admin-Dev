package us.kanddys.laia.modules.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import us.kanddys.laia.modules.ecommerce.model.OrderProduct;
import us.kanddys.laia.modules.ecommerce.model.OrderProductId;

@Repository
public interface OrderProductJpaRepository extends JpaRepository<OrderProduct, OrderProductId> {

   @Query(value = "SELECT COUNT(*) FROM orders_products ord WHERE ord.order_id = :orderId", nativeQuery = true)
   public Integer countByOrderId(@Param("orderId") Long orderId);

   @Query(value = "SELECT product_id FROM orders_products ord WHERE ord.order_id = :orderId", nativeQuery = true)
   public List<Long> findAllProductsIdByOrderId(@Param("orderId") Long orderId);

   @Modifying
   @Query(value = "UPDATE orders_products ord SET ord.quantity = :quantity WHERE ord.order_id = :orderId AND ord.product_id = :productId", nativeQuery = true)
   public Integer updateQuantityByOrderIdAndProductId(@Param("orderId") Long orderId,
         @Param("productId") Long productId, @Param("quantity") Integer quantity);

   @Query(value = "SELECT product_id FROM orders_products ord WHERE ord.order_id = :orderId AND ord.product_id = :productId", nativeQuery = true)
   public Long existOrderProductsByOrderIdAndProductId(@Param("orderId") Long orderId,
         @Param("productId") Long productId);

   @Query("SELECT total FROM Order WHERE id = :orderId")
   public Double findTotalByOrderId(@Param("orderId") Long orderId);
}
