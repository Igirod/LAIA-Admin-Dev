package us.kanddys.laia.modules.ecommerce.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import us.kanddys.laia.modules.ecommerce.model.Address;

@Repository
public class AddressCriteriaRepository {

   @Autowired
   private EntityManager entityManager;

   public List<Address> findAddressPaginated(Integer page, Long userId) {
      CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
      CriteriaQuery<Address> cQueryAddress = cBuilder.createQuery(Address.class);
      Root<Address> rAddress = cQueryAddress.from(Address.class);
      cQueryAddress.where(cBuilder.equal(rAddress.get("userId"), userId));
      cQueryAddress.select(rAddress);
      return entityManager.createQuery(cQueryAddress).setMaxResults(10)
            .setFirstResult((page - 1) * 10).getResultList();
   }
}
