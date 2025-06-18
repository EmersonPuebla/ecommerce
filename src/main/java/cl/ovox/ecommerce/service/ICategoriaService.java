package cl.ovox.ecommerce.service;

import java.util.List;

import cl.ovox.ecommerce.dto.CategoriaDTO;

public interface ICategoriaService {
    List<CategoriaDTO> findAll();
    CategoriaDTO findByNombre(String nombre);
    CategoriaDTO save(CategoriaDTO nombre);
    CategoriaDTO update(String nombre, CategoriaDTO categoria);
    void delete(String nombre);
}
