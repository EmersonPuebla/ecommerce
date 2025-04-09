package cl.ecommerce.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ecommerce.ecommerce.dto.PublicacionDTO;
import cl.ecommerce.ecommerce.repository.PublicacionRepository;
import cl.ecommerce.ecommerce.service.IPublicacionService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PublicacionServiceImpl implements IPublicacionService{

    @Autowired
    private PublicacionRepository publicacionRepository;

    public List<PublicacionDTO> findAll() {
        return publicacionRepository.findAll();
    }

    public PublicacionDTO findById(Integer id) {
        return publicacionRepository.findById(id).get();
    }

    public PublicacionDTO save(PublicacionDTO publicacion) {
        return publicacionRepository.save(publicacion);
    }

    public void delete(Integer id) {
        publicacionRepository.deleteById(id);
    }

}
