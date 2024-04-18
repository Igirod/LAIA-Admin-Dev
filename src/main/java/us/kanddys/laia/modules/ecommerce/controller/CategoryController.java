package us.kanddys.laia.modules.ecommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import us.kanddys.laia.modules.ecommerce.services.CategoryProductService;
import us.kanddys.laia.modules.ecommerce.services.CategoryService;

@Controller
public class CategoryController {

   @Autowired
   private CategoryService categoryService;

   @Autowired
   private CategoryProductService categoryProductService;

   @MutationMapping("cCategory")
   public Long createCategory(@Argument String title) {
      return categoryService.createCategory(title);
   }

   @MutationMapping("uCategory")
   public Integer updateCategory(@Argument Long categoryId, @Argument Optional<String> title) {
      return categoryService.updateCategory(categoryId, title);
   }

   @MutationMapping("dCategory")
   public Integer deleteCategory(@Argument Long categoryId) {
      return categoryService.deleteCategory(categoryId);
   }

   @MutationMapping("cCategoryProduct")
   public Integer createCategoryProduct(@Argument Long categoryId, @Argument Long productId) {
      return categoryProductService.createCategoryProduct(categoryId, productId);
   }

   @MutationMapping("dCategoryProduct")
   public Integer deleteCategoryProduct(@Argument Long categoryId, @Argument Long productId) {
      return categoryProductService.deleteCategoryProduct(categoryId, productId);
   }
}
