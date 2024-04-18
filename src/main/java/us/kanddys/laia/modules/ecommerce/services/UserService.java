package us.kanddys.laia.modules.ecommerce.services;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

/**
 * Esta interface contiene las obligaciones que debe implementar
 * la clase UserServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.1
 */
public interface UserService {

   /**
    * Este método actualiza los datos del usuario.
    * 
    * @param userId
    * @param email
    * @param password
    * @param phone
    * @param lastName
    * @param name
    * @return Integer
    */
   public Integer updateUser(Long userId, Optional<String> email, Optional<String> password, Optional<String> phone,
         Optional<String> lastName, Optional<String> name);

   /**
    * Este método tiene la obligación de actualizar la imagen de perfil del
    * usuario.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param userId
    * @param image
    * @return Integer
    */
   public Integer updateProfileImage(Long userId, MultipartFile image);
}
