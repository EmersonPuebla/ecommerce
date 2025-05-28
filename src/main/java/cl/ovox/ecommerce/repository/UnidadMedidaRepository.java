package cl.ovox.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.ovox.ecommerce.dto.UnidadMedidaDTO;

public interface UnidadMedidaRepository extends JpaRepository<UnidadMedidaDTO, Integer>  {

    Optional<UnidadMedidaDTO> findByNombre(String nombre);

    Optional<UnidadMedidaDTO> findBySimbolo(String simbolo);
}
