package cl.ovox.ecommerce.service;

import java.util.List;

import cl.ovox.ecommerce.dto.ProductoEstadoDTO;

public interface IProductoEstadoService {
    List<ProductoEstadoDTO> findAll();
    ProductoEstadoDTO findById(Integer id);
    ProductoEstadoDTO save(ProductoEstadoDTO color);
    ProductoEstadoDTO update(Integer id, ProductoEstadoDTO categoria);
    void delete(Integer id);
}
