package cl.ovox.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.ovox.ecommerce.dto.ProductoEstadoDTO;


public interface ProductoEstadoRepository extends JpaRepository<ProductoEstadoDTO, Integer> {

        Optional<ProductoEstadoDTO> findByNombre(String nombre);
}
