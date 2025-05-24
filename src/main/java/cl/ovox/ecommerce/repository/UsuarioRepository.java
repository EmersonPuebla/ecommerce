package cl.ovox.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.ovox.ecommerce.dto.UsuarioDTO;

public interface UsuarioRepository extends JpaRepository<UsuarioDTO, Integer> {

}
