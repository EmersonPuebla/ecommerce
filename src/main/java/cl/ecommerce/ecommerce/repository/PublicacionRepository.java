package cl.ecommerce.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.ecommerce.ecommerce.dto.PublicacionDTO;

public interface PublicacionRepository extends JpaRepository<PublicacionDTO, Integer> {

}
