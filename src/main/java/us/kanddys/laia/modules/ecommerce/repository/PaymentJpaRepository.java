package us.kanddys.laia.modules.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.Payment;

@Repository
public interface PaymentJpaRepository extends JpaRepository<Payment, Long> {

   @Query(value = "SELECT type FROM payments WHERE id = :paymentId", nativeQuery = true)
   public String findTypeByPaymentId(Long paymentId);
}
