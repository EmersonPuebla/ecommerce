package cl.ovox.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ovox.ecommerce.dto.ProductoEstadoDTO;
import cl.ovox.ecommerce.repository.ProductoEstadoRepository;
import cl.ovox.ecommerce.service.IProductoEstadoService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductoEstadoImpl implements IProductoEstadoService {

    @Autowired
    private ProductoEstadoRepository productoEstadoRepository;

    @Override
    public List<ProductoEstadoDTO> findAll() {
        return productoEstadoRepository.findAll();
    }

    @Override
    public ProductoEstadoDTO findById(Integer id) {
        return productoEstadoRepository.findById(id).orElse(null);
    }

    public ProductoEstadoDTO findByNombre(String nombre) {
        return productoEstadoRepository.findByNombre(nombre).orElse(null);
    }

    @Override
    public ProductoEstadoDTO save(ProductoEstadoDTO color) {
        String nombreNormalizado = color.getNombre().toLowerCase();
        color.setNombre(nombreNormalizado);

        Optional<ProductoEstadoDTO> existingProductoEstado = productoEstadoRepository.findByNombre(nombreNormalizado);

        if (existingProductoEstado.isPresent()) {
            if (color.getId() == null || !color.getId().equals(existingProductoEstado.get().getId())) {
                throw new IllegalArgumentException("El color '" + color.getNombre() + "' ya existe.");
            }
        }

        return productoEstadoRepository.save(color);
    }

    @Override
    public ProductoEstadoDTO update(Integer id, ProductoEstadoDTO color) {
        if (productoEstadoRepository.findById(id) != null) {
            return productoEstadoRepository.save(color);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        productoEstadoRepository.deleteById(id);
    }

    
}
