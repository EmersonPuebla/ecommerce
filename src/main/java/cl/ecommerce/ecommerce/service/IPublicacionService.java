package cl.ecommerce.ecommerce.service;

import java.util.List;

import cl.ecommerce.ecommerce.dto.PublicacionDTO;

public interface IPublicacionService {
    List<PublicacionDTO> findAll();
    PublicacionDTO findById(Integer id);
    PublicacionDTO save(PublicacionDTO publicacion);
    void delete(Integer id);
}
