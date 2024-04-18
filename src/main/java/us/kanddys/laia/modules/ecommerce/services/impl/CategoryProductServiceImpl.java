package us.kanddys.laia.modules.ecommerce.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.modules.ecommerce.model.CategoryProduct;
import us.kanddys.laia.modules.ecommerce.model.CategoryProductId;
import us.kanddys.laia.modules.ecommerce.repository.CategoryProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.CategoryProductService;

@Service
public class CategoryProductServiceImpl implements CategoryProductService {

   @Autowired
   private CategoryProductJpaRepository categoryProductJpaRepository;

   @Override
   public Integer createCategoryProduct(Long categoryId, Long productId) {
      categoryProductJpaRepository.save(new CategoryProduct(new CategoryProductId(categoryId, productId), null));
      return 1;
   }

   @Override
   public Integer deleteCategoryProduct(Long categoryId, Long productId) {
      categoryProductJpaRepository.deleteById(new CategoryProductId(categoryId, productId));
      return 1;
   }

}
