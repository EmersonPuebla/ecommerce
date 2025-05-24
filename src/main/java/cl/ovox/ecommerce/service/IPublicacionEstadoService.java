package cl.ovox.ecommerce.service;

import java.util.List;

import cl.ovox.ecommerce.dto.PublicacionEstadoDTO;

public interface IPublicacionEstadoService {
    List<PublicacionEstadoDTO> findAll();
    PublicacionEstadoDTO findById(Integer id);
    PublicacionEstadoDTO save(PublicacionEstadoDTO publicacionEst);
    void delete(Integer id);
}
