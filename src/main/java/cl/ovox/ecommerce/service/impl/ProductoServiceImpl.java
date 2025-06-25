package cl.ovox.ecommerce.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ovox.ecommerce.dto.CategoriaDTO;
import cl.ovox.ecommerce.dto.ColorDTO;
import cl.ovox.ecommerce.dto.ProductoDTO;
import cl.ovox.ecommerce.dto.ProductoEstadoDTO;
import cl.ovox.ecommerce.dto.UnidadMedidaDTO;
import cl.ovox.ecommerce.repository.CategoriaRepository;
import cl.ovox.ecommerce.repository.ColorRepository;
import cl.ovox.ecommerce.repository.ProductoEstadoRepository;
import cl.ovox.ecommerce.repository.ProductoRepository;
import cl.ovox.ecommerce.repository.UnidadMedidaRepository;
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

    @Autowired
    private ProductoEstadoRepository estadoRepository;

    @Autowired
    private UnidadMedidaRepository unidadMedidaRepository;

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
        
        
        /* === ESTADO =========================================================== */
        String nombreEstado = producto.getEstado()              
                                    .getNombre()
                                    .toUpperCase();              

        ProductoEstadoDTO estado = estadoRepository
                .findByNombre(nombreEstado)
                .orElseThrow(() -> new IllegalArgumentException(
                    "El estado '" + nombreEstado + "' no existe"));
                    
                    producto.setEstado(estado);              
                    
        /* === UNIDAD MEDIDA =========================================================== */
        String simbolo = producto.getMedida().getNombre().toUpperCase();

        UnidadMedidaDTO medida = unidadMedidaRepository.findBySimbolo(simbolo)
                .orElseThrow(() -> new IllegalArgumentException(
                    "La unidad de medida '" + simbolo + "' no existe"));
                    
                    producto.setMedida(medida);              
                        
        /* === COLORES ========================================================== */
        List<String> nombresColores = producto.getColores().stream()
                .map(c -> c.getNombre().toUpperCase())
                .toList();

        List<ColorDTO> coloresCompletos =
                colorRepository.findByNombreIn(nombresColores);
        producto.setColores(coloresCompletos);

        /* === CATEGORÍAS ======================================================= */
        List<String> nombresCategorias = producto.getCategorias().stream()
                .map(cat -> cat.getNombre().toUpperCase())
                .toList();

        List<CategoriaDTO> categoriasCompletas =
                categoriaRepository.findByNombreIn(nombresCategorias);
        producto.setCategorias(categoriasCompletas);

        /* === GUARDAR ========================================================== */
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