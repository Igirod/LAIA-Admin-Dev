package us.kanddys.laia.modules.ecommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import us.kanddys.laia.modules.ecommerce.controller.dto.RCheckEmailDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.RLoginUserDTO;
import us.kanddys.laia.modules.ecommerce.services.LoginService;
import us.kanddys.laia.modules.ecommerce.services.UserService;

@Controller
public class UserController {

   @Autowired
   private UserService userService;

   @Autowired
   private LoginService loginService;

   @QueryMapping("sqqCheck")
   public RCheckEmailDTO sqqCheck(@Argument Long userId, @Argument String email) {
      return loginService.sqqCheck(email);
   }

   @QueryMapping("sqqLogin")
   public RLoginUserDTO loginUser(@Argument Long userId, @Argument String password) {
      return loginService.sqqLogin(userId, password);
   }

   @MutationMapping("uUser")
   public Integer updateUser(@Argument Long userId, @Argument Optional<String> email,
         @Argument Optional<String> password, @Argument Optional<String> phone, @Argument Optional<String> lastName,
         @Argument Optional<String> name) {
      return userService.updateUser(userId, email, password, phone, lastName, name);
   }
}
