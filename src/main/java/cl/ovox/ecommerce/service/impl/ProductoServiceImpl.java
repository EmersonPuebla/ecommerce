package cl.ovox.ecommerce.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ovox.ecommerce.dto.ColorDTO;
import cl.ovox.ecommerce.dto.ProductoDTO;
import cl.ovox.ecommerce.repository.ColorRepository;
import cl.ovox.ecommerce.repository.ProductoRepository;
import cl.ovox.ecommerce.service.IProductoService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductoServiceImpl implements IProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ColorRepository colorRepository;

    public List<ProductoDTO> findAll() {
        return productoRepository.findAll();
    }

    public ProductoDTO findById(UUID id) {
        return productoRepository.findById(id).orElse(null);
    }

    public ProductoDTO save(ProductoDTO producto) {
        List<Integer> colorIds = producto.getColores().stream()
            .map(ColorDTO::getId)
            .toList();

        System.out.println("IDs de color recibidos: " + colorIds);

        List<ColorDTO> coloresCompletos = colorRepository.findAllById(colorIds);

        System.out.println("Colores encontrados en BD: " + coloresCompletos);

        producto.setColores(coloresCompletos);

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