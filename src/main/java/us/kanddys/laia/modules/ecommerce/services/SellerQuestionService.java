package us.kanddys.laia.modules.ecommerce.services;

import java.util.List;
import java.util.Optional;

import us.kanddys.laia.modules.ecommerce.controller.dto.SellerQuestionDTO;

/**
 * Esta interfaz define las obligaciones que debe implementar la clase
 * SellerQuestionServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.1
 */
public interface SellerQuestionService {

   /**
    * Este método se encarga de crear una nueva pregunta para un vendedor.
    *
    * @author Igirod0
    * @version 1.0.1
    * @param question
    * @param required
    * @param type
    * @param limit
    * @param productId
    * @param options
    * @return Integer
    */
   public Long createQuestion(String question, Optional<Integer> required, String type,
         Optional<Integer> limit, Long productId, Optional<List<String>> options);

   /**
    * Este método se encarga de eliminar una pregunta para un vendedor.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param questionId
    * @return Integer.
    */
   public Integer deleteQuestion(Long questionId);

   /**
    * Este método se encarga de modificar una pregunta para un vendedor.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param id
    * @param question
    * @return Integer
    */
   public Integer updateQuestion(Long questionId, Optional<String> question, Optional<String> type,
         Optional<Integer> limit,
         Optional<Integer> required);

   /**
    * Este método se encarga de obtener una pregunta de un vendedor por su valor
    * de pregunta y tipo.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param question
    * @param type
    * @return SellerQuestion
    */
   public Long getQuestionIdByQuestionAndType(String question, String type);

   /**
    * Este método se encarga de obtener una pregunta por su valor de pregunta
    * y tipo.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param question
    * @param type
    * @return SellerQuestionDTO
    */
   public SellerQuestionDTO getQuestionByQuestionAndType(String question, String type);

   public Integer updateAdminSellQuestions(Long id, Optional<String> value, Optional<String> type,
         Optional<Integer> required, Optional<Integer> limit, Optional<List<String>> options);

   /**
    * Este método se encarga de obtener las preguntas de un vendedor por su id de
    * producto asociado.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param productId
    * @return List<SellerQuestionDTO>
    */
   public List<SellerQuestionDTO> getAdminSellQuestions(Long productId);
}
