package cl.ovox.ecommerce.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ovox.ecommerce.dto.ImagenDTO;
import cl.ovox.ecommerce.repository.ImagenRepository;
import cl.ovox.ecommerce.service.IImagenService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ImagenServiceImpl implements IImagenService{
    @Autowired
    private ImagenRepository imagenRepository;
    
    public List<ImagenDTO> findAll() {
        return imagenRepository.findAll();
    }

    public ImagenDTO findById(UUID id) {
        return imagenRepository.findById(id).orElse(null);
    }

    public ImagenDTO save(ImagenDTO imagen) {
        return imagenRepository.save(imagen);
    }

    public ImagenDTO update(UUID id, ImagenDTO imagen) {
        if (imagenRepository.findById(id) != null) {
            return imagenRepository.save(imagen);
        }
        return null;
    }

    public void delete(UUID id) {
        imagenRepository.deleteById(id);
    }


}