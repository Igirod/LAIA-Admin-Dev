package us.kanddys.laia.modules.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import us.kanddys.laia.modules.ecommerce.model.Order;
import us.kanddys.laia.modules.ecommerce.model.Utils.Status;

@Repository
public class OrderCriteriaRepository {

   @Autowired
   private EntityManager entityManager;

   public List<Order> findOrdersPaginated(Integer page, Long merchantId, Optional<String> userEmail,
         Optional<Status> status) {
      CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
      CriteriaQuery<Order> cQueryinvoice = cBuilder.createQuery(Order.class);
      Root<Order> rInvoice = cQueryinvoice.from(Order.class);
      cQueryinvoice.where(cBuilder.equal(rInvoice.get("merchantId"), merchantId));
      if (userEmail.isPresent())
         cQueryinvoice.where(cBuilder.equal(rInvoice.get("userEmail"), userEmail.get()));
      if (status.isPresent())
         cQueryinvoice.where(cBuilder.equal(rInvoice.get("status"), status.get()));
      cQueryinvoice.select(rInvoice);
      return entityManager.createQuery(cQueryinvoice).setMaxResults(10)
            .setFirstResult((page - 1) * 10).getResultList();
   }
}
