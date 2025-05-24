package cl.ovox.ecommerce.service;

import java.util.List;

import cl.ovox.ecommerce.dto.PublicacionDTO;

public interface IPublicacionService {
    List<PublicacionDTO> findAll();
    PublicacionDTO findById(Integer id);
    PublicacionDTO update(Integer id, PublicacionDTO publicacion);
    PublicacionDTO save(PublicacionDTO publicacion);
    void delete(Integer id);
}
