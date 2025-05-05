package cl.ecommerce.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.ecommerce.ecommerce.dto.PublicacionEstadoDTO;
import cl.ecommerce.ecommerce.repository.PublicacionEstadoRepository;
import cl.ecommerce.ecommerce.service.IPublicacionEstadoService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PublicacionEstadoServiceImpl implements IPublicacionEstadoService{

    @Autowired
    private PublicacionEstadoRepository publicacionEstadoRepository;

    public List<PublicacionEstadoDTO> findAll() {
        return publicacionEstadoRepository.findAll();
    }

    public PublicacionEstadoDTO findById(Integer id) {
        return publicacionEstadoRepository.findById(id).get();
    }

    public PublicacionEstadoDTO save(PublicacionEstadoDTO publicacionEstado) {
        return publicacionEstadoRepository.save(publicacionEstado);
    }

    public void delete(Integer id) {
        publicacionEstadoRepository.deleteById(id);
    }

}
