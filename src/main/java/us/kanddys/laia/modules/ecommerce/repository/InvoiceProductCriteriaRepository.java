package us.kanddys.laia.modules.ecommerce.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import us.kanddys.laia.modules.ecommerce.model.InvoiceProduct;

@Repository
public class InvoiceProductCriteriaRepository {

   @Autowired
   private EntityManager entityManager;

   public List<InvoiceProduct> findInvoiceProductsByInvoiceId(Long InvoiceProductId, Integer page,
         Optional<Integer> limit) {
      List<Predicate> predicates = new ArrayList<>();
      CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
      CriteriaQuery<InvoiceProduct> cQueryInvoiceProduct = cBuilder.createQuery(InvoiceProduct.class);
      Root<InvoiceProduct> rInvoiceProduct = cQueryInvoiceProduct.from(InvoiceProduct.class);
      predicates.add(cBuilder.equal(rInvoiceProduct.get("id").get("invoiceId"), InvoiceProductId));
      cQueryInvoiceProduct.where(predicates.toArray(new Predicate[0]));
      return entityManager.createQuery(cQueryInvoiceProduct).setMaxResults(limit.isPresent() ? limit.get() : 10)
            .setFirstResult((page - 1) * (limit.isPresent() ? limit.get() : 10)).getResultList();
   }

   public List<InvoiceProduct> findInvoiceProductsByInvoiceId(Long InvoiceProductId) {
      List<Predicate> predicates = new ArrayList<>();
      CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
      CriteriaQuery<InvoiceProduct> cQueryInvoiceProduct = cBuilder.createQuery(InvoiceProduct.class);
      Root<InvoiceProduct> rInvoiceProduct = cQueryInvoiceProduct.from(InvoiceProduct.class);
      predicates.add(cBuilder.equal(rInvoiceProduct.get("id").get("invoiceId"), InvoiceProductId));
      cQueryInvoiceProduct.where(predicates.toArray(new Predicate[0]));
      return entityManager.createQuery(cQueryInvoiceProduct).getResultList();
   }

   @Transactional
   public int deleteProductsByInvoiceId(Long invoiceId) {
      CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
      CriteriaDelete<InvoiceProduct> criteriaDelete = criteriaBuilder.createCriteriaDelete(InvoiceProduct.class);
      Root<InvoiceProduct> root = criteriaDelete.from(InvoiceProduct.class);
      criteriaDelete.where(criteriaBuilder.equal(root.get("id").get("invoiceId"), invoiceId));
      int deletedCount = entityManager.createQuery(criteriaDelete).executeUpdate();
      return deletedCount;
   }
}
