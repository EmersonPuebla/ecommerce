package cl.ovox.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ovox.ecommerce.dto.ColorDTO;
import cl.ovox.ecommerce.repository.ColorRepository;
import cl.ovox.ecommerce.service.IColorService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ColorServiceImpl implements IColorService {

    @Autowired
    private ColorRepository colorRepository;

    @Override
    public List<ColorDTO> findAll() {
        return colorRepository.findAll();
    }

    @Override
    public ColorDTO findById(Integer id) {
        return colorRepository.findById(id).orElse(null);
    }

    @Override
    public ColorDTO save(ColorDTO color) {
        String nombreNormalizado = color.getNombre().toLowerCase();
        color.setNombre(nombreNormalizado);

        Optional<ColorDTO> existingColor = colorRepository.findByNombre(nombreNormalizado);

        if (existingColor.isPresent()) {
            if (color.getId() == null || !color.getId().equals(existingColor.get().getId())) {
                throw new IllegalArgumentException("El color '" + color.getNombre() + "' ya existe.");
            }
        }

        return colorRepository.save(color);
    }

    @Override
    public ColorDTO update(Integer id, ColorDTO color) {
        if (colorRepository.findById(id) != null) {
            return colorRepository.save(color);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        colorRepository.deleteById(id);
    }

    
}
