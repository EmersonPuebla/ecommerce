package cl.ecommerce.ecommerce.service;

import java.util.List;

import cl.ecommerce.ecommerce.dto.PublicacionEstadoDTO;

public interface IPublicacionEstadoService {
    List<PublicacionEstadoDTO> findAll();
    PublicacionEstadoDTO findById(Integer id);
    PublicacionEstadoDTO save(PublicacionEstadoDTO publicacionEst);
    void delete(Integer id);
}
