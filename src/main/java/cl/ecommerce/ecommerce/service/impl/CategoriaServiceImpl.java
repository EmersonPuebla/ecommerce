package cl.ecommerce.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ecommerce.ecommerce.dto.CategoriaDTO;
import cl.ecommerce.ecommerce.repository.CategoriaRepository;
import cl.ecommerce.ecommerce.service.ICategoriaService;
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
    public CategoriaDTO findById(Integer id) {
        return categoriaRepository.findById(id).orElse(null);
    }   

    @Override
    public CategoriaDTO save(CategoriaDTO producto) {
        return categoriaRepository.save(producto);
    }

    @Override
    public CategoriaDTO update(Integer id, CategoriaDTO categoria) {
        if (categoriaRepository.findById(id) != null) {
            return categoriaRepository.save(categoria);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        categoriaRepository.deleteById(id);
    }

    
}
