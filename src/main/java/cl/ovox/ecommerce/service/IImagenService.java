package cl.ovox.ecommerce.service;

import java.util.List;
import java.util.UUID;

import cl.ovox.ecommerce.dto.ImagenDTO;

public interface IImagenService {
    List<ImagenDTO> findAll();
    ImagenDTO findById(UUID id);
    ImagenDTO save(ImagenDTO imagen);
    ImagenDTO update(UUID id, ImagenDTO imagen);
    void delete(UUID id);
}
