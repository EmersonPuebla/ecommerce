package cl.ovox.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.ovox.ecommerce.dto.CategoriaDTO;

public interface CategoriaRepository extends JpaRepository<CategoriaDTO, Integer> {

}
