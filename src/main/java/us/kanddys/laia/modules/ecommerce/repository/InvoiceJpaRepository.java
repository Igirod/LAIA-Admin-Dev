package us.kanddys.laia.modules.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import us.kanddys.laia.modules.ecommerce.model.Invoice;

@Repository
public interface InvoiceJpaRepository extends JpaRepository<Invoice, Long> {

   @Modifying
   @Query(value = "UPDATE invoices SET status = ?2 WHERE id = ?1", nativeQuery = true)
   void updateInvoiceStatus(Long id, String status);

   @Modifying
   @Query(value = "UPDATE invoices SET note = ?2 WHERE id = ?1", nativeQuery = true)
   void updateInvoiceNote(Long id, String note);

   @Modifying
   @Query(value = "UPDATE invoices SET code = :code WHERE id = :orderId", nativeQuery = true)
   public void updateCodeByInvoiceId(@Param("code") String code, @Param("orderId") Long invoiceId);

   @Modifying
   @Query(value = "UPDATE invoices SET voucher = :voucher WHERE id = :orderId", nativeQuery = true)
   public void updateVoucherByInvoiceId(@Param("voucher") String voucher, @Param("orderId") Long invoiceId);

   @Query(value = "SELECT MAX(id) FROM invoices WHERE mer_id = :merchantId AND status IN ('PENDING', 'COMPLETE')", nativeQuery = true)
   public Long findMaxInvoiceIdByMerchantIdAndStatus(@Param("merchantId") Long merchantId);
}
