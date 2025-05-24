package cl.ovox.ecommerce.service;

import java.util.List;
import java.util.UUID;

import cl.ovox.ecommerce.dto.PublicacionDTO;

public interface IPublicacionService {
    List<PublicacionDTO> findAll();
    PublicacionDTO findById(UUID id);
    PublicacionDTO update(UUID id, PublicacionDTO publicacion);
    PublicacionDTO save(PublicacionDTO publicacion);
    void delete(UUID id);
}
