package us.kanddys.laia.modules.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.InvoiceProduct;
import us.kanddys.laia.modules.ecommerce.model.InvoiceProductId;

@Repository
public interface InvoiceProductJpaRepository extends JpaRepository<InvoiceProduct, InvoiceProductId> {
}
