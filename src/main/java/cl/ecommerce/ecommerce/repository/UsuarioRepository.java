package cl.ecommerce.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.ecommerce.ecommerce.dto.UsuarioDTO;

public interface UsuarioRepository extends JpaRepository<UsuarioDTO, Integer> {

}
