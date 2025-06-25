package cl.ovox.ecommerce.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.ovox.ecommerce.dto.ProductoDTO;

public interface ProductoRepository extends JpaRepository<ProductoDTO, UUID> {
    
    Optional<ProductoDTO> findBySku(String sku);

}
