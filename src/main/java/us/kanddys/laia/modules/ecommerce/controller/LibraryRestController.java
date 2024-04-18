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
import us.kanddys.laia.modules.ecommerce.services.LibraryService;

@RestController
@RequestMapping("/library")
@Tag(name = "Library Rest Controller", description = "Operaciones REST relacionadas a las bibliotecas.")
public class LibraryRestController {

   @Autowired
   private LibraryService libraryService;

   @Operation(description = "Servicio que tiene la obligación de obtener una biblioteca asociada a un usuario.")
   @Parameters({
         @Parameter(name = "userId", description = "Identificador del usuario", required = true, example = "1"),
         @Parameter(name = "libraryId", description = "Identificador de la biblioteca", required = true, example = "1") })
   @GetMapping("/gLibrary")
   public ResponseEntity<Map<String, Object>> gAdminSellLibrary(@RequestParam Long userId,
         @RequestParam Long libraryId) {
      return ResponseEntity.ok(libraryService.gAdminSellLibrary(userId, libraryId));
   }
}
