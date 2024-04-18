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
import us.kanddys.laia.modules.ecommerce.model.OrderProduct;

@Repository
public class OrderProductCriteriaRepository {

   @Autowired
   private EntityManager entityManager;

   public List<OrderProduct> findOrderProductsByOrderId(Long orderId) {
      List<Predicate> predicates = new ArrayList<>();
      CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
      CriteriaQuery<OrderProduct> cQueryOrderProduct = cBuilder.createQuery(OrderProduct.class);
      Root<OrderProduct> rOrderProduct = cQueryOrderProduct.from(OrderProduct.class);
      predicates.add(cBuilder.equal(rOrderProduct.get("id").get("orderId"), orderId));
      cQueryOrderProduct.where(predicates.toArray(new Predicate[0]));
      return entityManager.createQuery(cQueryOrderProduct).getResultList();
   }

   public List<OrderProduct> findOrderProductsByOrderId(Long orderId, Integer page,
         Optional<Integer> limit) {
      List<Predicate> predicates = new ArrayList<>();
      CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
      CriteriaQuery<OrderProduct> cQueryOrderProduct = cBuilder.createQuery(OrderProduct.class);
      Root<OrderProduct> rOrderProduct = cQueryOrderProduct.from(OrderProduct.class);
      predicates.add(cBuilder.equal(rOrderProduct.get("id").get("orderId"), orderId));
      cQueryOrderProduct.where(predicates.toArray(new Predicate[0]));
      return entityManager.createQuery(cQueryOrderProduct).setMaxResults(limit.isPresent() ? limit.get() : 10)
            .setFirstResult((page - 1) * (limit.isPresent() ? limit.get() : 10)).getResultList();
   }

   @Transactional
   public int deleteProductsByOrderId(Long orderId) {
      CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
      CriteriaDelete<OrderProduct> criteriaDelete = criteriaBuilder.createCriteriaDelete(OrderProduct.class);
      Root<OrderProduct> root = criteriaDelete.from(OrderProduct.class);
      criteriaDelete.where(criteriaBuilder.equal(root.get("id").get("orderId"), orderId));
      int deletedCount = entityManager.createQuery(criteriaDelete).executeUpdate();
      return deletedCount;
   }
}
