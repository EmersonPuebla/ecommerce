package cl.ovox.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.ovox.ecommerce.dto.PublicacionDTO;

public interface PublicacionRepository extends JpaRepository<PublicacionDTO, Integer> {

}
