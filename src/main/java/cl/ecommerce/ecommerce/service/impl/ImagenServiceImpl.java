package cl.ecommerce.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ecommerce.ecommerce.dto.ImagenDTO;
import cl.ecommerce.ecommerce.repository.ImagenRepository;
import cl.ecommerce.ecommerce.service.IImagenService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ImagenServiceImpl implements IImagenService{
    @Autowired
    private ImagenRepository imagenRepository;
    
    public List<ImagenDTO> findAll() {
        return imagenRepository.findAll();
    }

    public ImagenDTO findById(String id) {
        return imagenRepository.findById(id).orElse(null);
    }

    public ImagenDTO save(ImagenDTO imagen) {
        return imagenRepository.save(imagen);
    }

    public ImagenDTO update(String id, ImagenDTO imagen) {
        if (imagenRepository.findById(id) != null) {
            return imagenRepository.save(imagen);
        }
        return null;
    }

    public void delete(String id) {
        imagenRepository.deleteById(id);
    }


}