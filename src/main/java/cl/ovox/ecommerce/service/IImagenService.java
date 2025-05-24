package cl.ovox.ecommerce.service;

import java.util.List;

import cl.ovox.ecommerce.dto.ImagenDTO;

public interface IImagenService {
    List<ImagenDTO> findAll();
    ImagenDTO findById(String id);
    ImagenDTO save(ImagenDTO imagen);
    ImagenDTO update(String id, ImagenDTO imagen);
    void delete(String id);
}
