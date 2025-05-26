package cl.ovox.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ovox.ecommerce.dto.CategoriaDTO;
import cl.ovox.ecommerce.repository.CategoriaRepository;
import cl.ovox.ecommerce.service.ICategoriaService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoriaServiceImpl implements ICategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<CategoriaDTO> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public CategoriaDTO findByNombre(String nombre) {
        return categoriaRepository.findByNombre(nombre).orElse(null);
    }   

    @Override
    public CategoriaDTO save(CategoriaDTO producto) {
        return categoriaRepository.save(producto);
    }

    @Override
    public CategoriaDTO update(String nombre, CategoriaDTO categoria) {
        if (categoriaRepository.findByNombre(nombre) != null) {
            return categoriaRepository.save(categoria);
        }
        return null;
    }

    @Override
    public void delete(String nombre) {
        categoriaRepository.deleteByNombre(nombre);
    }

    
}
