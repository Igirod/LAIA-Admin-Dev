package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.modules.ecommerce.controller.dto.MemoryDTO;
import us.kanddys.laia.modules.ecommerce.repository.MemoryJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.MemoryService;

@Service
public class MemoryServiceImpl implements MemoryService {

   @Autowired
   private MemoryJpaRepository memoryJpaRepository;

   @Override
   public List<MemoryDTO> getAllMemories() {
      return memoryJpaRepository.findAll().stream().map(MemoryDTO::new).collect(Collectors.toList());
   }

}
