package cl.ecommerce.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.ecommerce.ecommerce.dto.CategoriaDTO;

public interface CategoriaRepository extends JpaRepository<CategoriaDTO, Integer> {

}
