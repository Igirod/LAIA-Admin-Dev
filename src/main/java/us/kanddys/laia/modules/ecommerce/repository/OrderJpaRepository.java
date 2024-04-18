package us.kanddys.laia.modules.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.Order;
import us.kanddys.laia.modules.ecommerce.model.Utils.Status;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Long> {

   @Query(value = "SELECT id from orders where id = :orderId", nativeQuery = true)
   public Long existByOrderId(@Param("orderId") Long orderId);

   public Order findOrderByUserIdAndMerchantIdAndStatus(Long userId, Long merchantId, Status status);

   @Modifying
   @Query(value = "UPDATE orders SET message = :message WHERE id = :orderId", nativeQuery = true)
   void updateMessageByOrderId(@Param("message") String message, @Param("orderId") Long orderId);

   @Modifying
   @Query(value = "UPDATE orders SET payment_id = :paymentId WHERE id = :orderId", nativeQuery = true)
   public Integer updatePaymentByOrderId(Long paymentId, Long orderId);

   @Modifying
   @Query(value = "UPDATE orders SET note = :note WHERE id = :orderId", nativeQuery = true)
   public void updateNoteByOrderId(String note, Long orderId);

   @Modifying
   @Query(value = "UPDATE orders SET status = :status WHERE id = :orderId", nativeQuery = true)
   public void updateStatusByOrderId(String status, Long orderId);

   @Modifying
   @Query(value = "UPDATE orders SET voucher = :voucher WHERE id = :orderId", nativeQuery = true)
   public void updateVoucherByOrderId(String voucher, Long orderId);

   @Modifying
   @Query(value = "UPDATE orders SET total = :total WHERE id = :orderId", nativeQuery = true)
   public void updateTotal(Long orderId, Double total);

   @Modifying
   @Query(value = "UPDATE orders SET user_id = :userId WHERE id = :orderId", nativeQuery = true)
   public void updateUserId(Long orderId, Long userId);

   @Modifying
   @Query(value = "UPDATE orders SET address_number = :addressNumber, type = :type,address_lat = :lat, address_lng = :lng, address_direction = :direction WHERE id = :orderId", nativeQuery = true)
   public void updateAddressByOrderId(String direction, String lat, String lng, Long orderId, Integer addressNumber,
         String type);

   @Query(value = "SELECT * FROM orders WHERE user_id = :userId AND mer_id = :merchantId AND status = :status", nativeQuery = true)
   public Order findOrderIdByUserIdAndMerchantIdAndStatus(@Param("userId") Long userId,
         @Param("merchantId") Long merchantId, @Param("status") String status);

   @Modifying
   @Query(value = "UPDATE orders SET reservation = :reservation, batch_id = :batchId WHERE id = :orderId", nativeQuery = true)
   public void updateReservationAndBatchIdByOrderId(@Param("reservation") String reservation,
         @Param("batchId") Long batchId, @Param("orderId") Long orderId);

   @Modifying
   @Query(value = "UPDATE orders SET batch_id = :batchId, payment_id = :paymentId, status = :status WHERE id = :orderId", nativeQuery = true)
   public void updateBatchIdAndPaymentIdAndStatusByOrderId(@Param("batchId") Long batchId,
         @Param("paymentId") Long paymentId, @Param("status") String status, @Param("orderId") Long orderId);
}
