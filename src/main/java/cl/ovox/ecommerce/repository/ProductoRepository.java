package cl.ovox.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.ovox.ecommerce.dto.ProductoDTO;


public interface ProductoRepository extends JpaRepository<ProductoDTO, Integer> {

}
