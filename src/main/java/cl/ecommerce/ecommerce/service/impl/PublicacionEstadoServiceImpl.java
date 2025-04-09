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
    private PublicacionEstadoRepository publicacionEstRepository;

    public List<PublicacionEstadoDTO> findAll() {
        return publicacionEstRepository.findAll();
    }

    public PublicacionEstadoDTO findById(Integer id) {
        return publicacionEstRepository.findById(id).get();
    }

    public PublicacionEstadoDTO save(PublicacionEstadoDTO publicacionEst) {
        return publicacionEstRepository.save(publicacionEst);
    }

    public void delete(Integer id) {
        publicacionEstRepository.deleteById(id);
    }

}
