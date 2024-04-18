package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import us.kanddys.laia.modules.ecommerce.controller.dto.CollectionMiniatureDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.CreateCollectionBodyDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.LibraryCollectionConfigurationDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.LibraryCollectionFilterDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.LibraryCollectionMiniatureDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.LibraryCollectionOrderDTO;
import us.kanddys.laia.modules.ecommerce.exception.ExistLibraryCollectionTitleException;
import us.kanddys.laia.modules.ecommerce.exception.LibraryCollectionNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.LibraryNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.LibraryTypeCollectionNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.LibraryUserNotEqualException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.model.FilterBuyer;
import us.kanddys.laia.modules.ecommerce.model.Library;
import us.kanddys.laia.modules.ecommerce.model.LibraryCollection;
import us.kanddys.laia.modules.ecommerce.repository.FilterBuyerJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.LibraryCollectionJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.LibraryJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.BuyerLibraryService;
import us.kanddys.laia.modules.ecommerce.services.LibraryCollectionService;

/**
 * Esta clase implementa las obligaciones de la interfaz
 * LibraryCollectionService.
 * 
 * @author Igirod0
 * @version 1.0.4
 */
@Service
public class LibraryCollectionServiceImpl implements LibraryCollectionService {

   @Autowired
   private LibraryCollectionJpaRepository libraryCollectionJpaRepository;

   @Autowired
   private LibraryJpaRepository libraryJpaRepository;

   @Autowired
   private BuyerLibraryService buyerLibraryService;

   @Autowired
   private FilterBuyerJpaRepository filterBuyerJpaRepository;

   @Override
   public Integer uCollectionRename(Long collectionId, String title, Long library) {
      Optional<Long> existlibraryCollectionId = libraryCollectionJpaRepository
            .existLibraryCollectionWithLibraryIdAndTitleAndNotLibraryCollectionId(collectionId, title, library);
      if (existlibraryCollectionId.isEmpty()) {
         Optional<LibraryCollection> libraryCollection = libraryCollectionJpaRepository.findById(collectionId);
         if (libraryCollection.isEmpty()) {
            throw new LibraryCollectionNotFoundException(ExceptionMessage.LIBRARY_COLLECTION_NOT_FOUND);
         }
         libraryCollection.get().setTitle(title);
         libraryCollectionJpaRepository.save(libraryCollection.get());
      } else {
         throw new ExistLibraryCollectionTitleException(ExceptionMessage.EXIST_LIBRARY_COLLECTION_TITLE);
      }
      return 1;
   }

   @Override
   public Integer uCollectionOrder(Long collectionId, Integer ascDsc, Optional<String> orderProps) {
      Optional<LibraryCollection> libraryCollection = libraryCollectionJpaRepository.findById(collectionId);
      if (libraryCollection.isEmpty() || !libraryCollection.get().getConf().equals(0)) {
         return 0;
      }
      libraryCollection.get().setAscDsc(ascDsc);
      if (orderProps.isPresent() && libraryCollection.get().getConf().equals(0)) {
         libraryCollection.get().setOrdering(orderProps.get());
      }
      libraryCollectionJpaRepository.save(libraryCollection.get());
      return 1;
   }

   @Override
   public LibraryCollectionConfigurationDTO gCollectionConfiguration(Long collectionId, Long libraryId, Long userId) {
      LibraryCollectionConfigurationDTO newLibraryCollectionConfigurationDTO = new LibraryCollectionConfigurationDTO();
      Optional<LibraryCollection> libraryCollection = libraryCollectionJpaRepository.findById(collectionId);
      if (libraryCollection.isEmpty())
         throw new LibraryCollectionNotFoundException(ExceptionMessage.LIBRARY_COLLECTION_NOT_FOUND);
      Optional<Library> library = libraryJpaRepository.findById(libraryCollection.get().getLibraryId());
      if (library.isEmpty())
         throw new LibraryNotFoundException(ExceptionMessage.LIBRARY_NOT_FOUND);
      if (!library.get().getUserId().equals(userId)) {
         newLibraryCollectionConfigurationDTO.setOperation(0);
         return newLibraryCollectionConfigurationDTO;
      }
      newLibraryCollectionConfigurationDTO.setTitle(libraryCollection.get().getTitle());
      newLibraryCollectionConfigurationDTO.setConf(libraryCollection.get().getConf());
      newLibraryCollectionConfigurationDTO.setAscDsc(libraryCollection.get().getAscDsc());
      newLibraryCollectionConfigurationDTO
            .setOrder(List.of(libraryCollection.get().getOrdering().split(" ")).get(0).toLowerCase());
      newLibraryCollectionConfigurationDTO.setOperation(1);
      switch (library.get().getTypeCollection()) {
         case 1:
            Optional<FilterBuyer> filterBuyer = filterBuyerJpaRepository.findById(collectionId);
            if (filterBuyer.isEmpty()) {
               newLibraryCollectionConfigurationDTO.setFilter(new LibraryCollectionFilterDTO(null, null));
            } else {
               newLibraryCollectionConfigurationDTO
                     .setFilter(new LibraryCollectionFilterDTO((filterBuyer.get().getId()),
                           (filterBuyer.get().getAlias() != null ? filterBuyer.get().getAlias() : null)));
            }
            break;
         default:
            throw new LibraryTypeCollectionNotFoundException(ExceptionMessage.LIBRARY_TYPE_COLLECTION_NOT_FOUND);
      }
      return newLibraryCollectionConfigurationDTO;
   }

   @Override
   public LibraryCollectionOrderDTO gCollectionOrder(Long collectionId, Long libraryId, Long userId) {
      Optional<LibraryCollection> libraryCollection = libraryCollectionJpaRepository.findById(collectionId);
      Optional<Library> library = libraryJpaRepository.findById(libraryId);
      if (library.isEmpty())
         throw new LibraryNotFoundException(ExceptionMessage.LIBRARY_NOT_FOUND);
      if (libraryCollection.isEmpty())
         throw new LibraryCollectionNotFoundException(ExceptionMessage.LIBRARY_COLLECTION_NOT_FOUND);
      if (!library.get().getUserId().equals(userId))
         return new LibraryCollectionOrderDTO(null, null, 0, new HashSet<>());
      return new LibraryCollectionOrderDTO(
            (libraryCollection.get().getTitle() != null ? libraryCollection.get().getTitle() : null),
            libraryCollection.get().getAscDsc(), 1,
            List.of(libraryCollection.get().getOrdering().split(" ")).stream().collect(Collectors.toSet()));
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Map<String, Object> cCollection(CreateCollectionBodyDTO createCollectionBodyDTO, Integer duplicateCollection,
         Optional<String> duplicateCollectionTitle) {
      Map<String, Object> collectionData = new HashMap<String, Object>();
      Optional<Library> library = libraryJpaRepository.findById(createCollectionBodyDTO.getLibraryId());
      if (library.isEmpty()) {
         throw new LibraryNotFoundException(ExceptionMessage.LIBRARY_NOT_FOUND);
      }
      Integer collectionsCount = libraryCollectionJpaRepository.countByLibraryId(createCollectionBodyDTO.getLibraryId())
            + 1;
      LibraryCollection libraryCollection = new LibraryCollection();
      libraryCollection.setId(null);
      libraryCollection.setLibraryId(createCollectionBodyDTO.getLibraryId());
      libraryCollection.setConf(0);
      libraryCollection.setAscDsc(1);
      libraryCollection.setMiniatureHeader(library.get().getMiniatureHeader());
      libraryCollection.setMiniatureTitle(library.get().getMiniatureTitle());
      libraryCollection.setMiniatureSubtitle(library.get().getMiniatureSubtitle());
      libraryCollection.setOrdering(List.of(library.get().getMiniature().split(" ")).stream().map(String::toUpperCase)
            .collect(Collectors.toList()).stream().collect(Collectors.joining(" ")));
      if (duplicateCollectionTitle.isPresent() && duplicateCollection.equals(1)) {
         libraryCollection.setTitle(duplicateCollectionTitle.get());
      } else {
         libraryCollection.setTitle("Personalizado #" + collectionsCount.toString());
      }
      libraryCollectionJpaRepository.save(libraryCollection);
      collectionData.put("id", libraryCollection.getId());
      collectionData.put("title", (libraryCollection.getTitle() != null ? libraryCollection.getTitle() : null));
      collectionData.put("miniatureHeader", libraryCollection.getMiniatureHeader().split(" "));
      collectionData.put("miniatureTitle", libraryCollection.getMiniatureTitle().split(" "));
      collectionData.put("miniatureSubtitle", libraryCollection.getMiniatureSubtitle().split(" "));
      getCollectionCountItemsByType(library.get().getTypeCollection(), library.get().getUserId(),
            libraryCollection, collectionData);
      getCollectionItemsByTypeAndPageAndMaxResults(library.get().getTypeCollection(), library.get().getUserId(),
            libraryCollection, collectionData, 10, 1);
      return collectionData;
   }

   @Override
   public Map<String, Object> gCollection(Long collectionId, Long libraryId, Long userId) {
      Map<String, Object> response = new HashMap<String, Object>();
      Map<String, Object> collectionData = new HashMap<String, Object>();
      Optional<Library> library = libraryJpaRepository.findById(libraryId);
      if (library.isEmpty()) {
         throw new LibraryNotFoundException(ExceptionMessage.LIBRARY_NOT_FOUND);
      }
      Optional<LibraryCollection> libraryCollection = libraryCollectionJpaRepository.findById(collectionId);
      if (libraryCollection.isEmpty()) {
         throw new LibraryCollectionNotFoundException(ExceptionMessage.LIBRARY_COLLECTION_NOT_FOUND);
      }
      if (!library.get().getUserId().equals(userId)) {
         response.put("operation", 0);
         return response;
      }
      collectionData.put("title", (libraryCollection != null ? libraryCollection.get().getTitle() : null));
      collectionData.put("miniatureHeader", libraryCollection.get().getMiniatureHeader() != null
            ? libraryCollection.get().getMiniatureHeader().split(" ")
            : null);
      collectionData.put("miniatureTitle", libraryCollection.get().getMiniatureHeader() != null
            ? libraryCollection.get().getMiniatureTitle().split(" ")
            : null);
      collectionData.put("miniatureSubtitle", libraryCollection.get().getMiniatureHeader() != null
            ? libraryCollection.get().getMiniatureSubtitle().split(" ")
            : null);
      getCollectionCountItemsByType(library.get().getTypeCollection(), userId, libraryCollection.get(),
            collectionData);
      getCollectionItemsByTypeAndPageAndMaxResults(library.get().getTypeCollection(), userId, libraryCollection.get(),
            collectionData,
            10, 1);
      response.put("collection", collectionData);
      response.put("operation", 1);
      return response;
   }

   /**
    * Método privado que obtiene las propiedades count y items de una colección
    * segun el tipo de biblioteca.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param typeCollection
    * @param userId
    * @param libraryCollection
    * @param collectionData
    */
   private void getCollectionCountItemsByType(Integer typeCollection, Long userId,
         LibraryCollection libraryCollection, Map<String, Object> collectionData) {
      switch (typeCollection) {
         case 1:
            collectionData.put("count",
                  buyerLibraryService.gBuyerLibraryCollectionTotalItems(libraryCollection.getId(),
                        (libraryCollection.getMiniatureHeader() != null
                              ? libraryCollection.getMiniatureHeader().split(" ")
                              : null),
                        (libraryCollection.getMiniatureTitle() != null
                              ? libraryCollection.getMiniatureTitle().split(" ")
                              : null),
                        (libraryCollection.getMiniatureSubtitle() != null
                              ? libraryCollection.getMiniatureSubtitle().split(" ")
                              : null),
                        libraryCollection.getOrdering(),
                        libraryCollection.getAscDsc(), userId));
            break;
         default:
            break;
      }
   }

   private void getCollectionItemsByTypeAndPageAndMaxResults(Integer typeCollection, Long userId,
         LibraryCollection libraryCollection, Map<String, Object> collectionData, Integer maxResults, Integer page) {
      switch (typeCollection) {
         case 1:
            collectionData.put("items",
                  buyerLibraryService.gBuyerLibraryCollectionItems(libraryCollection.getId(),
                        (libraryCollection.getMiniatureHeader() != null
                              ? libraryCollection.getMiniatureHeader().split(" ")
                              : null),
                        (libraryCollection.getMiniatureTitle() != null
                              ? libraryCollection.getMiniatureTitle().split(" ")
                              : null),
                        libraryCollection.getMiniatureSubtitle() != null
                              ? libraryCollection.getMiniatureSubtitle().split(" ")
                              : null,
                        libraryCollection.getOrdering(),
                        libraryCollection.getAscDsc(),
                        userId, page, maxResults));
            break;
         default:
            break;
      }
   }

   @Override
   public Map<String, Object> gCollectionElements(Long collectionId, Integer page) {
      Optional<LibraryCollection> libraryCollection = libraryCollectionJpaRepository.findById(collectionId);
      Optional<Library> library = libraryJpaRepository.findById(libraryCollection.get().getLibraryId());
      if (libraryCollection.isEmpty()) {
         throw new LibraryCollectionNotFoundException(ExceptionMessage.LIBRARY_COLLECTION_NOT_FOUND);
      }
      if (library.isEmpty()) {
         throw new LibraryNotFoundException(ExceptionMessage.LIBRARY_NOT_FOUND);
      }
      Map<String, Object> response = new HashMap<String, Object>();
      response.put("libraryId", library.get().getId());
      getCollectionItemsByTypeAndPageAndMaxResults(library.get().getTypeCollection(), library.get().getUserId(),
            libraryCollection.get(),
            response,
            10, page);
      return response;
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer dCollection(Long collectionId) {
      Optional<LibraryCollection> libraryCollection = libraryCollectionJpaRepository.findById(collectionId);
      if (libraryCollection.isEmpty()) {
         throw new LibraryCollectionNotFoundException(ExceptionMessage.LIBRARY_COLLECTION_NOT_FOUND);
      }
      libraryCollectionJpaRepository.deleteById(libraryCollection.get().getId());
      return 1;
   }

   @Override
   public LibraryCollectionMiniatureDTO gCollectionMiniature(Long collectionId, Long libraryId, Long userId) {
      LibraryCollectionMiniatureDTO newLibraryCollectionMiniatureDTO = new LibraryCollectionMiniatureDTO();
      Optional<Library> library = libraryJpaRepository.findById(libraryId);
      Optional<LibraryCollection> libraryCollection = libraryCollectionJpaRepository.findById(collectionId);
      if (library.isEmpty()) {
         throw new LibraryNotFoundException(ExceptionMessage.LIBRARY_NOT_FOUND);
      }
      if (libraryCollection.isEmpty()) {
         throw new LibraryCollectionNotFoundException(ExceptionMessage.LIBRARY_COLLECTION_NOT_FOUND);
      }
      if (!library.get().getUserId().equals(userId)) {
         newLibraryCollectionMiniatureDTO.setOperation(0);
         return newLibraryCollectionMiniatureDTO;
      }
      newLibraryCollectionMiniatureDTO.setTitle((libraryCollection.get().getTitle() != null
            ? libraryCollection.get().getTitle()
            : null));
      newLibraryCollectionMiniatureDTO.setOperation(1);
      newLibraryCollectionMiniatureDTO
            .setCollectionMiniature(new CollectionMiniatureDTO(libraryCollection.get().getId(),
                  (library.get().getMiniature() != null
                        ? library.get().getMiniature().split(" ")
                        : null),
                  (libraryCollection.get().getMiniatureHeader() != null
                        ? libraryCollection.get().getMiniatureHeader().split(" ")
                        : null),
                  (libraryCollection.get().getMiniatureSubtitle() != null
                        ? libraryCollection.get().getMiniatureSubtitle().split(" ")
                        : null),
                  (libraryCollection.get().getMiniatureTitle() != null
                        ? libraryCollection.get().getMiniatureTitle().split(" ")
                        : null)));
      return newLibraryCollectionMiniatureDTO;
   }

   @Override
   public Integer uCollectionMiniature(Long collectionId, String miniatureHeader, String miniatureTitle,
         String miniatureSubtitle) {
      Optional<LibraryCollection> libraryCollection = libraryCollectionJpaRepository.findById(collectionId);
      if (libraryCollection.isEmpty()) {
         throw new LibraryCollectionNotFoundException(ExceptionMessage.LIBRARY_COLLECTION_NOT_FOUND);
      }
      libraryCollection.get().setMiniatureHeader(miniatureHeader);
      libraryCollection.get().setMiniatureTitle(miniatureTitle);
      libraryCollection.get().setMiniatureSubtitle(miniatureSubtitle);
      libraryCollectionJpaRepository.save(libraryCollection.get());
      return 1;
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Map<String, Object> cCollectionRef(CreateCollectionBodyDTO createCollectionBodyDTO) {
      Optional<Library> library = libraryJpaRepository.findById(createCollectionBodyDTO.getLibraryId());
      Optional<LibraryCollection> libraryCollection = libraryCollectionJpaRepository
            .findById(createCollectionBodyDTO.getCollectionId());
      if (library.isEmpty()) {
         throw new LibraryNotFoundException(ExceptionMessage.LIBRARY_NOT_FOUND);
      }
      if (libraryCollection.isEmpty()) {
         throw new LibraryCollectionNotFoundException(ExceptionMessage.LIBRARY_COLLECTION_NOT_FOUND);
      }
      if (!library.get().getUserId().equals(createCollectionBodyDTO.getUserId())) {
         throw new LibraryUserNotEqualException(ExceptionMessage.LIBRARY_USER_NOT_EQUAL);
      }
      Integer duplicateCollectionCount = libraryCollectionJpaRepository.countByLibraryIdAndTitle(
            createCollectionBodyDTO.getLibraryId(), libraryCollection.get().getTitle());
      String newDuplicateCollectionTitle = "";
      if ((libraryCollection.get().getTitle().length() + 12 + duplicateCollectionCount.toString().length()) >= 20) {
         newDuplicateCollectionTitle = libraryCollection.get().getTitle().substring(0,
               20 - 12 - duplicateCollectionCount.toString().length()) + "... (copia#"
               + duplicateCollectionCount.toString() + ")";
      } else {
         newDuplicateCollectionTitle = libraryCollection.get().getTitle() + "... (copia#"
               + duplicateCollectionCount.toString() + ")";
      }
      return cCollection(createCollectionBodyDTO, 1, Optional.of(newDuplicateCollectionTitle));
   }

   @Override
   public Map<String, Object> gCollectionFilter(Long user, Long filterId) {
      return null;
   }
}
