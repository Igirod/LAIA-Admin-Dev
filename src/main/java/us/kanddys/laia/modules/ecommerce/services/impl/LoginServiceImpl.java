package us.kanddys.laia.modules.ecommerce.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import us.kanddys.laia.modules.ecommerce.controller.dto.RCheckEmailDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.RLoginUserDTO;
import us.kanddys.laia.modules.ecommerce.model.User;
import us.kanddys.laia.modules.ecommerce.repository.UserJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.LoginService;

/**
 * Esta clase implementa las obligaciones de la interfaz LoginService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class LoginServiceImpl implements LoginService {

   @Autowired
   private UserJpaRepository userJpaRepository;

   @Override
   public RLoginUserDTO sqqLogin(Long userId, String password) {
      var userAtributes = userJpaRepository.findPhoneAndImageAndPasswordByUserId(userId);
      if (userAtributes.get("password").toString().equals(password))
         return new RLoginUserDTO(
               (userAtributes.get("phone") == null ? null : Integer.valueOf(userAtributes.get("phone").toString())),
               (userAtributes.get("image") == null ? null : userAtributes.get("image").toString()), 1);
      return new RLoginUserDTO(null, null, 0);
   }

   @Override
   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   public RCheckEmailDTO sqqCheck(@Argument String email) {
      var userAtributes = userJpaRepository.findUserIdAndNameAndLastNameAndDifferentCreationByEmail(email);
      if (userAtributes.size() == 0) {
         return new RCheckEmailDTO(0, userJpaRepository.save(new User(true, email)).getId(), null, null);
      } else {
         if (userAtributes.get("different_creation").toString().equals("0")) {
            return new RCheckEmailDTO(1,
                  (userAtributes.get("id") == null ? null : Long.valueOf(userAtributes.get("id").toString())),
                  (userAtributes.get("name") == null ? null : userAtributes.get("name").toString()),
                  (userAtributes.get("last_name") == null ? null : userAtributes.get("last_name").toString()));
         } else {
            userJpaRepository.updateDifferentCreationByUserId(Long.valueOf(userAtributes.get("id").toString()), 0);
            return new RCheckEmailDTO(0,
                  (userAtributes.get("id") == null ? null : Long.valueOf(userAtributes.get("id").toString())),
                  (userAtributes.get("name") == null ? null : userAtributes.get("name").toString()),
                  (userAtributes.get("last_name") == null ? null : userAtributes.get("last_name").toString()));
         }
      }
   }
}
