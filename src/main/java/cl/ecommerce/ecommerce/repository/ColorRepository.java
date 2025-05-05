package cl.ecommerce.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.ecommerce.ecommerce.dto.ColorDTO;

public interface ColorRepository extends JpaRepository<ColorDTO, Integer>{
    
}
