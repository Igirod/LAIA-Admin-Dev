package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.modules.ecommerce.controller.dto.SellerQuestionDTO;
import us.kanddys.laia.modules.ecommerce.exception.SellerQuestionNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.model.SellerQuestion;
import us.kanddys.laia.modules.ecommerce.model.SellerQuestionMultipleOption;
import us.kanddys.laia.modules.ecommerce.repository.SellerQuestionJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.SellerQuestionMultipleOptionJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.SellerQuestionService;

/**
 * Esta clase implementa las obligaciones definidas en la interfaz
 * SellerQuestionService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class SellerQuestionServiceImpl implements SellerQuestionService {

   @Autowired
   private SellerQuestionJpaRepository sellerQuestionJpaRepository;

   @Autowired
   private SellerQuestionMultipleOptionJpaRepository sellerQuestionMultipleOptionJpaRepository;

   @Override
   public Long createQuestion(String question, Optional<Integer> required, String type,
         Optional<Integer> limit, Long productId, Optional<List<String>> options) {
      var sellerQuestionId = sellerQuestionJpaRepository
            .save(new SellerQuestion(null, question, required.orElse(null), type, limit.orElse(null),
                  productId))
            .getId();
      if (type.equals("MULTIPLE")) {
         if (limit.isEmpty())
            throw new IllegalArgumentException(ExceptionMessage.LIMIT_REQUIRED);
         if (options.isEmpty())
            throw new IllegalArgumentException(ExceptionMessage.OPTIONS_REQUIRED);
         sellerQuestionMultipleOptionJpaRepository.saveAll(options.get().stream()
               .map(option -> new SellerQuestionMultipleOption(null, sellerQuestionId, option))
               .toList());
      }
      return sellerQuestionId;
   }

   @Override
   public Integer deleteQuestion(Long id) {
      sellerQuestionJpaRepository.deleteById(id);
      return 1;
   }

   @Override
   public Integer updateQuestion(Long questionId, Optional<String> question, Optional<String> type,
         Optional<Integer> limit, Optional<Integer> required) {
      var existSellerQuestion = sellerQuestionJpaRepository.findById(questionId);
      if (existSellerQuestion.isEmpty())
         throw new SellerQuestionNotFoundException(ExceptionMessage.SELLER_QUESTION_NOT_FOUND);
      if (question != null) {
         var sellerQuestionToUpdate = existSellerQuestion.get();
         sellerQuestionToUpdate.setQuestion(question.get());
         sellerQuestionToUpdate.setType(type.orElse(null));
         sellerQuestionToUpdate.setLimit(limit.orElse(null));
         sellerQuestionToUpdate.setRequired(required.orElse(null));
         sellerQuestionJpaRepository.save(sellerQuestionToUpdate);
      } else {
         deleteQuestion(questionId);
      }
      return 1;
   }

   @Override
   public Long getQuestionIdByQuestionAndType(String question, String type) {
      return sellerQuestionJpaRepository.findQuestionIdByQuestionAndType(question, type);
   }

   @Override
   public SellerQuestionDTO getQuestionByQuestionAndType(String question, String type) {
      return new SellerQuestionDTO(sellerQuestionJpaRepository.findQuestionByQuestionAndType(question, type));
   }

   @Override
   public Integer updateAdminSellQuestions(Long id, Optional<String> value, Optional<String> type,
         Optional<Integer> required, Optional<Integer> limit, Optional<List<String>> options) {
      if (value.isEmpty() && type.isEmpty() && required.isEmpty() && limit.isEmpty() && options.isEmpty()) {
         if (sellerQuestionJpaRepository.existsById(id)) {
            sellerQuestionJpaRepository.deleteById(id);
         }
      } else {
         var sellerQuestion = sellerQuestionJpaRepository.findById(id);
         if (sellerQuestion.isEmpty())
            throw new SellerQuestionNotFoundException(ExceptionMessage.SELLER_QUESTION_NOT_FOUND);
         var sellerQuestionToUpdate = sellerQuestion.get();
         value.ifPresent(sellerQuestionToUpdate::setQuestion);
         type.ifPresent(sellerQuestionToUpdate::setType);
         required.ifPresent(sellerQuestionToUpdate::setRequired);
         limit.ifPresent(sellerQuestionToUpdate::setLimit);
         sellerQuestionJpaRepository.save(sellerQuestionToUpdate);
         if (type.isPresent() && type.get().equals("MULTIPLE")) {
            if (limit.isEmpty())
               throw new IllegalArgumentException(ExceptionMessage.LIMIT_REQUIRED);
            if (options.isEmpty())
               throw new IllegalArgumentException(ExceptionMessage.OPTIONS_REQUIRED);
            sellerQuestionMultipleOptionJpaRepository.deleteBySellerQuestionId(id);
            sellerQuestionMultipleOptionJpaRepository.saveAll(options.get().stream()
                  .map(option -> new SellerQuestionMultipleOption(null, id, option)).toList());
         }
      }
      return 1;
   }

   @Override
   public List<SellerQuestionDTO> getAdminSellQuestions(Long productId) {
      var productQuestions = sellerQuestionJpaRepository.findQuestionByProductId(productId).stream()
            .map(SellerQuestionDTO::new)
            .toList();
      productQuestions.forEach(question -> {
         if (question.getType().equals("MULTIPLE")) {
            question.setOptions(
                  sellerQuestionMultipleOptionJpaRepository.findBySellerQuestionId(question.getId()));
         }
      });
      return productQuestions;
   }
}
