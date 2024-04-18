package us.kanddys.laia.modules.ecommerce.repository;

import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.Merchant;

@Repository
public interface MerchantJpaRepository extends JpaRepository<Merchant, Long> {

   public Optional<Merchant> findBySlug(String slug);

   @Query(value = "SELECT id FROM merchants WHERE id = :merchantId", nativeQuery = true)
   public Long existByMerchantId(Long merchantId);

   @Query(value = "SELECT id, title, address FROM merchants WHERE slug = :slug", nativeQuery = true)
   public Map<String, Object> findMerchantIdAndTitleAndAddress(String slug);

   @Query(value = "SELECT id, title FROM merchants WHERE slug = :slug", nativeQuery = true)
   public Map<String, Object> findMerchantIdAndTitle(String slug);

   @Query(value = "SELECT address FROM merchants WHERE id = :merchantId", nativeQuery = true)
   public String findAddressByMerchantId(Long merchantId);

   @Query(value = "SELECT lat, lng, address FROM merchants WHERE id = :merchantId", nativeQuery = true)
   public Map<String, Object> findLatAndLngAndAddressByMerchantId(Long merchantId);

   @Query(value = "SELECT mer_email FROM merchants WHERE id = :merchantId", nativeQuery = true)
   public String findEmailByMerchantId(Long merchantId);

   @Query(value = "SELECT mer_email, user_id, slug FROM merchants WHERE id = :merchantId", nativeQuery = true)
   public Map<String, Object> findEmailAndUserIdAndSlugByMerchantId(Long merchantId);

   @Query(value = "SELECT id FROM merchants WHERE user_id = :userId", nativeQuery = true)
   public Long existUserId(Long userId);
}
