package cl.ovox.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

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
    if (categoriaRepository.findByNombre(producto.getNombre()).isPresent()) {
        return null; 
    }
    return categoriaRepository.save(producto);
}

@Override
public CategoriaDTO update(String nombre, CategoriaDTO categoriaActualizada) {
    Optional<CategoriaDTO> optionalExistente = categoriaRepository.findByNombre(nombre);
    if (optionalExistente.isEmpty()) {
        return null;
    }

    CategoriaDTO existente = optionalExistente.get();
    existente.setNombre(categoriaActualizada.getNombre());

    return categoriaRepository.save(existente);
}

    @Override
    public void delete(String nombre) {
        categoriaRepository.deleteByNombre(nombre);
    }

    
}
