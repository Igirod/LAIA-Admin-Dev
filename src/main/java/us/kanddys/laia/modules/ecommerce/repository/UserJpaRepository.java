package us.kanddys.laia.modules.ecommerce.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {

   @Query(value = "SELECT id FROM users WHERE email = :userEmail", nativeQuery = true)
   public Long existByUserEmail(String userEmail);

   @Query(value = "SELECT id FROM users WHERE email = :userEmail AND password = :password", nativeQuery = true)
   public Integer existByUserEmail(String userEmail, String password);

   @Query(value = "SELECT id FROM users WHERE email = :userEmail", nativeQuery = true)
   public Long findByUserEmail(String userEmail);

   @Modifying
   @Query(value = "UPDATE users SET password = :password WHERE id = :id", nativeQuery = true)
   public void updatePasswordByUserId(Long id, String password);

   @Modifying
   @Query(value = "UPDATE users SET email = :email WHERE id = :id", nativeQuery = true)
   public void updateEmailByUserId(Long id, String email);

   @Query(value = "SELECT password FROM users WHERE id = :id", nativeQuery = true)
   public String getPasswordByUserId(Long id);

   @Modifying
   @Query(value = "UPDATE users SET email = :email WHERE id = :userId", nativeQuery = true)
   public void updateUserEmail(Long userId, String email);

   @Modifying
   @Query(value = "UPDATE users SET image = :image WHERE id = :userId", nativeQuery = true)
   public void updateUserImage(Long userId, String image);

   @Query(value = "SELECT * FROM users WHERE id = :userId", nativeQuery = true)
   public User findUserById(Long userId);

   @Query(value = "SELECT id, name, last_name FROM users WHERE email = :email", nativeQuery = true)
   public Map<String, Object> findUserIdAndNameAndLastNameByEmail(String email);

   @Query(value = "SELECT email FROM users WHERE id = :userId", nativeQuery = true)
   public String findEmailByUserId(Long userId);

   @Query(value = "SELECT name, last_name, email FROM users WHERE id = :userId", nativeQuery = true)
   public Map<String, Object> findUserNameAndLastNameAndEmailById(Long userId);

   @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
   public User findUserByEmail(String email);

   @Query(value = "SELECT phone, image, password FROM users WHERE id = :userId", nativeQuery = true)
   public Map<String, Object> findPhoneAndImageAndPasswordByUserId(Long userId);

   @Query(value = "SELECT id FROM users WHERE id = :id", nativeQuery = true)
   public Long existByUserId(Long id);

   @Query(value = "SELECT id FROM users WHERE phone = :phone", nativeQuery = true)
   public Long existByUserPhone(String phone);

   @Query(value = "SELECT mer_email, mer_slug FROM users WHERE id = :userId", nativeQuery = true)
   public Map<String, Object> findMerchantEmailAndSlugByUserId(Long userId);

   @Query(value = "SELECT id, name, last_name, different_creation FROM users WHERE email = :email", nativeQuery = true)
   public Map<String, Object> findUserIdAndNameAndLastNameAndDifferentCreationByEmail(String email);

   @Modifying
   @Query(value = "UPDATE users SET different_creation = :differentCreation WHERE id = :userId", nativeQuery = true)
   public void updateDifferentCreationByUserId(Long userId, Integer differentCreation);

   @Query(value = "SELECT mer_phone, mer_slug, mer_email FROM users WHERE id = :userId", nativeQuery = true)
   public Map<String, Object> findMerchantPhoneAndSlugAndEmailByUserId(Long userId);
}
