package us.kanddys.laia.modules.ecommerce.services.storage.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import us.kanddys.laia.modules.ecommerce.services.storage.FirebaseStorageService;

/**
 * Esta clase implementa las obligaciones de la interface
 * FirebaseStorageService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class FirebaseStorageServiceImpl implements FirebaseStorageService {

   public String uploadFile(MultipartFile multipartFile, String imageName, String folderName) {
      return null;
   }
}
