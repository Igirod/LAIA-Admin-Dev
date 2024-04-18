package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.modules.ecommerce.exception.CategoryNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.model.Category;
import us.kanddys.laia.modules.ecommerce.repository.CategoryJpaRepostory;
import us.kanddys.laia.modules.ecommerce.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

   @Autowired
   private CategoryJpaRepostory categoryJpaRepostory;

   @Override
   public Long createCategory(String title) {
      if (categoryJpaRepostory.existsByTitle(title)) {
         throw new CategoryNotFoundException(ExceptionMessage.EXISTING_CATEGORY);
      }
      return categoryJpaRepostory.save(new Category(null, title)).getId();

   }

   @Override
   public Integer updateCategory(Long id, Optional<String> title) {
      Optional<Category> category = categoryJpaRepostory.findById(id);
      if (category.isPresent()) {
         var categoryToUpdate = category.get();
         title.ifPresent(categoryToUpdate::setTitle);
         categoryJpaRepostory.save(categoryToUpdate);
      } else {
         throw new CategoryNotFoundException(ExceptionMessage.CATEGORY_NOT_FOUND);
      }
      return 1;
   }

   @Override
   public Integer deleteCategory(Long id) {
      categoryJpaRepostory.deleteById(id);
      return 1;
   }

   @Override
   public Long getCategoryIdByTitle(String title) {
      return categoryJpaRepostory.findCategoryIdByTitle(title);
   }
}
