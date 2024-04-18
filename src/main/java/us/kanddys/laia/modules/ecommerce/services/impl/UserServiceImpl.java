package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import us.kanddys.laia.modules.ecommerce.repository.UserJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.UserService;
import us.kanddys.laia.modules.ecommerce.services.storage.FirebaseStorageService;

@Service
public class UserServiceImpl implements UserService {

   @Autowired
   private UserJpaRepository userJpaRepository;

   @Autowired
   private FirebaseStorageService firebaseStorageService;

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer updateUser(Long userId, Optional<String> email, Optional<String> password, Optional<String> phone,
         Optional<String> lastName, Optional<String> name) {
      var user = userJpaRepository.findById(userId);
      if (user.isPresent()) {
         var userToUpdate = user.get();
         email.ifPresent(userToUpdate::setMail);
         password.ifPresent(userToUpdate::setPassword);
         phone.ifPresent(userToUpdate::setPhone);
         lastName.ifPresent(userToUpdate::setLastName);
         name.ifPresent(userToUpdate::setName);
         userJpaRepository.save(userToUpdate);
         return 1;
      } else
         return 0;
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer updateProfileImage(Long userId, MultipartFile image) {
      if (userJpaRepository.existsById(userId)) {
         userJpaRepository.updateUserImage(userId,
               firebaseStorageService.uploadFile(image, "user-image-" + userId.toString() + "-" + UUID.randomUUID(),
                     "userImages"));
         return 1;
      } else
         return 0;
   }
}
