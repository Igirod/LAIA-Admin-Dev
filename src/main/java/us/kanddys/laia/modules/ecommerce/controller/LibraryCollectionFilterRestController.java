package us.kanddys.laia.modules.ecommerce.controller;

import java.util.Map;

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
import us.kanddys.laia.modules.ecommerce.services.LibraryCollectionFilterService;

@RestController
@RequestMapping("/library-collection-filter")
@Tag(name = "Library Collection Filter Rest Controller", description = "Operaciones REST relacionadas a los filtros de las colecciones de biblioteca.")
public class LibraryCollectionFilterRestController {

   @Autowired
   private LibraryCollectionFilterService libraryCollectionFilterService;

   @Operation(description = "Servicio que tiene la obligación de obtener un filtro de una colección asociada a una biblioteca.")
   @Parameters({
         @Parameter(name = "collectionId", description = "Identificador de la colección", required = true, example = "1")
   })
   @GetMapping("/gCollectionFilter")
   public ResponseEntity<Map<String, Object>> gAdminSellCollectionFilter(@RequestParam Long collectionId) {
      // TODO: TERMINAR.
      // ResponseEntity.ok(libraryCollectionFilterService.gAdminSellCollectionFilter(collectionId))
      return null;
   }
}
