package cl.ecommerce.ecommerce.service;

import java.util.List;

import cl.ecommerce.ecommerce.dto.ImagenDTO;

public interface IImagenService {
    List<ImagenDTO> findAll();
    ImagenDTO findById(String id);
    ImagenDTO save(ImagenDTO imagen);
    ImagenDTO update(String id, ImagenDTO imagen);
    void delete(String id);
}
