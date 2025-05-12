package cl.ecommerce.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.ecommerce.ecommerce.dto.ImagenDTO;

public interface ImagenRepository extends JpaRepository<ImagenDTO, String>{
    
}
