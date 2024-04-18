package us.kanddys.laia.modules.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.TableCondition;

@Repository
public interface TableConditionJpaRepository extends JpaRepository<TableCondition, Long> {
   @Query(value = "SELECT * FROM table_conditions WHERE library_id = ?1", nativeQuery = true)
   public List<TableCondition> findByLibraryId(Long libraryId);
}
