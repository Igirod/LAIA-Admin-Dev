package us.kanddys.laia.modules.ecommerce.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import us.kanddys.laia.modules.ecommerce.controller.dto.CreateCollectionBodyDTO;
import us.kanddys.laia.modules.ecommerce.services.LibraryCollectionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/library-collection")
@Tag(name = "Library Collection Rest Controller", description = "Operaciones REST relacionadas a las colecciones de biblioteca.")
public class LibraryCollectionRestController {

   @Autowired
   private LibraryCollectionService libraryCollectionService;

   @Operation(description = "Servicio que tiene la obligación de crear una colección asociada a una biblioteca.")
   @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Cuerpo de la petición para la creación de una colección.")
   @PostMapping("/cCollection")
   public ResponseEntity<Map<String, Object>> cCollection(
         @RequestBody CreateCollectionBodyDTO createCollectionBodyDTO) {
      try {
         return ResponseEntity.ok(libraryCollectionService.cCollection(createCollectionBodyDTO, 0, Optional.empty()));
      } catch (RuntimeException e) {
         return ResponseEntity.internalServerError().body(Map.of("message", e.getMessage()));
      } catch (Exception e) {
         return ResponseEntity.internalServerError().body(Map.of("message", e.getMessage()));
      }
   }

   @Operation(description = "Servicio que tiene la obligación de obtener una colección asociada a una biblioteca.")
   @Parameters({
         @Parameter(name = "collectionId", description = "Identificador de la colección", required = true, example = "1"),
         @Parameter(name = "libraryId", description = "Identificador de la biblioteca", required = true, example = "2"),
         @Parameter(name = "userId", description = "Identificador del usuario dueño de la biblioteca", required = true, example = "3")
   })
   @GetMapping("/gCollection")
   public ResponseEntity<Map<String, Object>> gCollection(@RequestParam Long collectionId, @RequestParam Long libraryId,
         @RequestParam Long userId) {
      try {
         return ResponseEntity.ok(libraryCollectionService.gCollection(collectionId, libraryId, userId));
      } catch (RuntimeException e) {
         return ResponseEntity.internalServerError().body(Map.of("message", e.getMessage()));
      } catch (Exception e) {
         return ResponseEntity.internalServerError().body(Map.of("message", e.getMessage()));
      }
   }

   @Operation(description = "Servicio que tiene la obligación de obtener los primeros diez items de una colección asociada a una biblioteca.")
   @Parameters({
         @Parameter(name = "collectionId", description = "Identificador de la colección", required = true, example = "1"),
         @Parameter(name = "page", description = "Número de págin", required = true, example = "1"),
   })
   @GetMapping("/gCollectionElements")
   public ResponseEntity<Map<String, Object>> gCollectionElements(@RequestParam Long collectionId,
         @RequestParam Integer page) {
      try {
         return ResponseEntity.ok(libraryCollectionService.gCollectionElements(collectionId, page));
      } catch (RuntimeException e) {
         return ResponseEntity.internalServerError().body(Map.of("message", e.getMessage()));
      } catch (Exception e) {
         return ResponseEntity.internalServerError().body(Map.of("message", e.getMessage()));
      }
   }

   @Operation(description = "Servicio que tiene la obligación de crear una colección asociada a una biblioteca.")
   @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Cuerpo de la petición para la creación de una colección.")
   @PostMapping("/cCollectionRef")
   public ResponseEntity<Map<String, Object>> cCollectionRef(
         @RequestBody CreateCollectionBodyDTO createCollectionBodyDTO) {
      try {
         return ResponseEntity.ok(libraryCollectionService.cCollectionRef(createCollectionBodyDTO));
      } catch (RuntimeException e) {
         return ResponseEntity.internalServerError().body(Map.of("message", e.getMessage()));
      } catch (Exception e) {
         return ResponseEntity.internalServerError().body(Map.of("message", e.getMessage()));
      }
   }

   public ResponseEntity<Map<String, Object>> gCollectionFilter(@RequestParam Long user, @RequestParam Long filterId) {
      try {
         return ResponseEntity.ok(libraryCollectionService.gCollectionFilter(user, filterId));
      } catch (RuntimeException e) {
         return ResponseEntity.internalServerError().body(Map.of("message", e.getMessage()));
      } catch (Exception e) {
         return ResponseEntity.internalServerError().body(Map.of("message", e.getMessage()));
      }
   }
}
