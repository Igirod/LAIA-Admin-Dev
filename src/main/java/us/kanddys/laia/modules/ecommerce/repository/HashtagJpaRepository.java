package us.kanddys.laia.modules.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.Hashtag;

@Repository
public interface HashtagJpaRepository extends JpaRepository<Hashtag, Long> {

   @Query(value = "SELECT id FROM hashtags WHERE value = ?1", nativeQuery = true)
   public Long findByValue(String value);
}
