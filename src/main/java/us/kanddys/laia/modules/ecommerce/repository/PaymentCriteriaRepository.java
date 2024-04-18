package us.kanddys.laia.modules.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import us.kanddys.laia.modules.ecommerce.model.Payment;

@Repository
public class PaymentCriteriaRepository {

   @Autowired
   private EntityManager entityManager;

   public List<Payment> findPaymentsPaginated(Integer page, Optional<Long> merchantId, Optional<Integer> status) {
      CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
      CriteriaQuery<Payment> cQueryPayment = cBuilder.createQuery(Payment.class);
      Root<Payment> rPayment = cQueryPayment.from(Payment.class);
      if (merchantId.isPresent()) cQueryPayment.where(cBuilder.equal(rPayment.get("merchantId"), merchantId.get()));
      if (status.isPresent()) cQueryPayment.where(cBuilder.equal(rPayment.get("status"), status.get()));
      cQueryPayment.select(rPayment);
      return entityManager.createQuery(cQueryPayment).setMaxResults(10)
            .setFirstResult((page - 1) * 10).getResultList();
   }
}
