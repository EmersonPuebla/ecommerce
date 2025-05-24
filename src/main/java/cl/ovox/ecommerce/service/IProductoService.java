package cl.ovox.ecommerce.service;

import java.util.List;
import java.util.UUID;

import cl.ovox.ecommerce.dto.ProductoDTO;

public interface IProductoService {
    List<ProductoDTO> findAll();
    ProductoDTO findById(UUID id);
    ProductoDTO save(ProductoDTO producto);
    ProductoDTO update(UUID id, ProductoDTO producto);
    void delete(UUID id);
}
