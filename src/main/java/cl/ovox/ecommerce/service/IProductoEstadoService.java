package cl.ovox.ecommerce.service;

import java.util.List;

import cl.ovox.ecommerce.dto.ProductoEstadoDTO;

public interface IProductoEstadoService {
    List<ProductoEstadoDTO> findAll();
    ProductoEstadoDTO findById(Integer id);
    ProductoEstadoDTO save(ProductoEstadoDTO productoEstado);
    ProductoEstadoDTO update(Integer id, ProductoEstadoDTO productoEstado);
    boolean delete(Integer id);
}
