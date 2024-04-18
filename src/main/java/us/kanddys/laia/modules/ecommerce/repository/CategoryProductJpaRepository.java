package us.kanddys.laia.modules.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.CategoryProduct;
import us.kanddys.laia.modules.ecommerce.model.CategoryProductId;

@Repository
public interface CategoryProductJpaRepository extends JpaRepository<CategoryProduct, CategoryProductId> {
}
