package us.kanddys.laia.modules.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.KeyWord;

@Repository
public interface KeyWordJpaRepository extends JpaRepository<KeyWord, Long> {

   @Query(value = "SELECT id FROM key_words WHERE word = ?1", nativeQuery = true)
   public Long findKeyWordIdByWord(String word);

   @Query(value = "SELECT * FROM key_words k WHERE k.word IN :words", nativeQuery = true)
   public List<KeyWord> findKeyWordIdByWords(@Param("words") List<String> words);

   @Query(value = "SELECT word FROM key_words k WHERE k.id IN :keyWordIds", nativeQuery = true)
   public List<String> findKeyWordByKeywordIds(@Param("keyWordIds") List<Long> keyWordIds);
}
