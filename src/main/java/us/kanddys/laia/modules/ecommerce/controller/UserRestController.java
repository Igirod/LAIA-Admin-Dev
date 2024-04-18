package us.kanddys.laia.modules.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import us.kanddys.laia.modules.ecommerce.services.UserService;

@RestController
@RequestMapping("/user")
@Tag(name = "User Rest controller", description = "Operaciones Rest relacionadas al usuario.")
public class UserRestController {

   @Autowired
   private UserService userService;

   @Operation(description = "Servicio que tiene como obligación actualizar la imagen asociada al usuario.")
   @Parameters({
         @Parameter(name = "image", description = "Imagen a actualizar", required = true, example = "image"),
         @Parameter(name = "userId", description = "Identificador del usuario", required = true, example = "1")
   })
   @ApiResponse(description = "Devuelve un 1 si la imagen fue actualizada correctamente.")
   @RequestMapping(method = { RequestMethod.POST }, value = "/upload", produces = {
         "application/json" }, consumes = { "multipart/form-data" })
   public Integer uploadUserImage(@RequestPart MultipartFile image, @RequestPart String userId) {
      return userService.updateProfileImage(Long.valueOf(userId), image);
   }
}
