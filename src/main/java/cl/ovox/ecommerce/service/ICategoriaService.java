package cl.ovox.ecommerce.service;

import java.util.List;

import cl.ovox.ecommerce.dto.CategoriaDTO;

public interface ICategoriaService {
    List<CategoriaDTO> findAll();
    CategoriaDTO findById(Integer id);
    CategoriaDTO save(CategoriaDTO producto);
    CategoriaDTO update(Integer id, CategoriaDTO categoria);
    void delete(Integer id);
}
