package cl.ovox.ecommerce.service;

import java.util.List;
import java.util.UUID;

import cl.ovox.ecommerce.dto.CategoriaDTO;

public interface ICategoriaService {
    List<CategoriaDTO> findAll();
    CategoriaDTO findById(UUID id);
    CategoriaDTO save(CategoriaDTO producto);
    CategoriaDTO update(UUID id, CategoriaDTO categoria);
    void delete(UUID id);
}
