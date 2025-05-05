package cl.ecommerce.ecommerce.service;

import java.util.List;

import cl.ecommerce.ecommerce.dto.CategoriaDTO;

public interface ICategoriaService {
    List<CategoriaDTO> findAll();
    CategoriaDTO findById(Integer id);
    CategoriaDTO save(CategoriaDTO producto);
    CategoriaDTO update(Integer id, CategoriaDTO categoria);
    void delete(Integer id);
}
