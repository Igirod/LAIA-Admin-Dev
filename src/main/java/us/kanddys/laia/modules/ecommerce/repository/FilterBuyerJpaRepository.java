package us.kanddys.laia.modules.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.FilterBuyer;

@Repository
public interface FilterBuyerJpaRepository extends JpaRepository<FilterBuyer, Long> {

   @Query("SELECT fb FROM FilterBuyer fb WHERE fb.collectionId = ?1")
   public Optional<FilterBuyer> findByCollectionId(Long collectionId);
}
