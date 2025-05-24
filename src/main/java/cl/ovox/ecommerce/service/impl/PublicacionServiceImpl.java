package cl.ovox.ecommerce.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ovox.ecommerce.dto.PublicacionDTO;
import cl.ovox.ecommerce.repository.PublicacionRepository;
import cl.ovox.ecommerce.service.IPublicacionService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PublicacionServiceImpl implements IPublicacionService{

    @Autowired
    private PublicacionRepository publicacionRepository;

    public List<PublicacionDTO> findAll() {
        return publicacionRepository.findAll();
    }

    public PublicacionDTO findById(UUID id) {
        return publicacionRepository.findById(id).get();
    }

    public PublicacionDTO save(PublicacionDTO publicacion) {
        return publicacionRepository.save(publicacion);
    }

    public PublicacionDTO update(UUID id, PublicacionDTO publicacion){
        if (publicacionRepository.findById(id) != null){
            return publicacionRepository.save(publicacion);
        }
        return null;   
    }

    public void delete(UUID id) {
        publicacionRepository.deleteById(id);
    }

}
