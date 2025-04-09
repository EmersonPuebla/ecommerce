package cl.ecommerce.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.ecommerce.ecommerce.dto.ProductoDTO;


public interface ProductoRepository extends JpaRepository<ProductoDTO, Integer> {

}
