package cl.ecommerce.ecommerce.service;

import java.util.List;

import cl.ecommerce.ecommerce.dto.ProductoDTO;

public interface IProductoService {
    List<ProductoDTO> findAll();
    ProductoDTO findById(Integer id);
    ProductoDTO save(ProductoDTO producto);
    ProductoDTO update(Integer id, ProductoDTO producto);
    void delete(Integer id);
}
