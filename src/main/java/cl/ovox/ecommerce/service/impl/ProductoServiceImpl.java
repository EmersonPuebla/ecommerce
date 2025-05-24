package cl.ovox.ecommerce.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ovox.ecommerce.dto.ProductoDTO;
import cl.ovox.ecommerce.repository.ProductoRepository;
import cl.ovox.ecommerce.service.IProductoService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductoServiceImpl implements IProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<ProductoDTO> findAll() {
        return productoRepository.findAll();
    }

    public ProductoDTO findById(UUID id) {
        return productoRepository.findById(id).orElse(null);
    }

    public ProductoDTO save(ProductoDTO producto) {
        return productoRepository.save(producto);
    }

    public ProductoDTO update(UUID id, ProductoDTO producto) {
        if (productoRepository.findById(id) != null) {
            return productoRepository.save(producto);
        }
        return null;
    }

    public void delete(UUID id) {
        productoRepository.deleteById(id);
    }
}