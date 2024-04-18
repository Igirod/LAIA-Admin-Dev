package us.kanddys.laia.modules.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.Memory;

@Repository
public interface MemoryJpaRepository extends JpaRepository<Memory, Long>{}
