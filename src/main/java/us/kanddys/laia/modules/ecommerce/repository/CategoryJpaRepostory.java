package us.kanddys.laia.modules.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.Category;

@Repository
public interface CategoryJpaRepostory extends JpaRepository<Category, Long> {

   @Query(value = "SELECT id FROM categories WHERE title = ?1", nativeQuery = true)
   public Long findCategoryIdByTitle(String title);

   public boolean existsByTitle(String title);
}
