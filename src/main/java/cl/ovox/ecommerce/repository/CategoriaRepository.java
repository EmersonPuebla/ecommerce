package cl.ovox.ecommerce.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.ovox.ecommerce.dto.CategoriaDTO;

public interface CategoriaRepository extends JpaRepository<CategoriaDTO, UUID> {

    Optional<CategoriaDTO> findByNombre(String nombre);
    
    Optional<CategoriaDTO> deleteByNombre(String nombre);

}
