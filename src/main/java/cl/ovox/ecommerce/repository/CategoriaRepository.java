package cl.ovox.ecommerce.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.ovox.ecommerce.dto.CategoriaDTO;

public interface CategoriaRepository extends JpaRepository<CategoriaDTO, UUID> {

    Optional<CategoriaDTO> findByNombre(String nombre);

    List<CategoriaDTO> findByNombreIn(List<String> nombres);
    
    Optional<CategoriaDTO> deleteByNombre(String nombre);

}
