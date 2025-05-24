package cl.ovox.ecommerce.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.ovox.ecommerce.dto.OpinionDTO;

public interface OpinionRepository extends JpaRepository<OpinionDTO, UUID>{
    
}
