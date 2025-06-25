package cl.ovox.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.ovox.ecommerce.dto.ColorDTO;

public interface ColorRepository extends JpaRepository<ColorDTO, Integer>{
    
    Optional<ColorDTO> findByNombre(String nombre);

    List<ColorDTO> findByNombreIn(List<String> nombres);
}
