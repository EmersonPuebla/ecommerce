package cl.ovox.ecommerce.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ovox.ecommerce.dto.CategoriaDTO;
import cl.ovox.ecommerce.dto.ColorDTO;
import cl.ovox.ecommerce.dto.ProductoDTO;
import cl.ovox.ecommerce.repository.CategoriaRepository;
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

    @Autowired
    private CategoriaRepository categoriaRepository;

    public ProductoDTO findBySku(String sku) {
        return productoRepository.findBySku(sku).orElse(null);
    }

    public List<ProductoDTO> findAll() {
        return productoRepository.findAll();
    }

    public ProductoDTO findById(UUID id) {
        return productoRepository.findById(id).orElse(null);
    }

    public ProductoDTO save(ProductoDTO producto) {

        /* === COLORES ========================================================= */
        List<String> nombresColores = producto.getColores().stream()
            .map(ColorDTO::getNombre)
            .map(String::toUpperCase)      // opcional: uniformar mayúsculas
            .toList();

        List<ColorDTO> coloresCompletos = colorRepository.findByNombreIn(nombresColores);
        producto.setColores(coloresCompletos);

        /* === CATEGORÍAS ====================================================== */
        List<String> nombresCategorias = producto.getCategorias().stream()
            .map(CategoriaDTO::getNombre)
            .map(String::toUpperCase)
            .toList();

        List<CategoriaDTO> categoriasCompletas = categoriaRepository.findByNombreIn(nombresCategorias);
        producto.setCategorias(categoriasCompletas);

        /* === GUARDAR ========================================================= */
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