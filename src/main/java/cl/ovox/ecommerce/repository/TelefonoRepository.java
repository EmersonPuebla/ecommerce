package cl.ovox.ecommerce.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.ovox.ecommerce.dto.TelefonoDTO;

public interface TelefonoRepository extends JpaRepository<TelefonoDTO, UUID>{

}
