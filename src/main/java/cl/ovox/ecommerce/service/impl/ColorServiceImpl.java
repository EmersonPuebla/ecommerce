package cl.ovox.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    
    public ColorDTO findByNombre(String nombre){
        if (nombre == null || nombre.trim().isEmpty()) {
            return null;
        }
        return colorRepository.findByNombre(nombre).orElse(null);
    }

    @Override
    public ColorDTO save(ColorDTO color) {

        Optional<ColorDTO> existingColor = colorRepository.findByNombre(color.getNombre());

        if (existingColor.isPresent()) {
            if (color.getId() == null || !color.getId().equals(existingColor.get().getId())) {
                throw new IllegalArgumentException("El color '" + color.getNombre() + "' ya existe.");
            }
        }

        if (color.getNombre() == null || color.getNombre() .trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del color no puede ser nulo o vacío.");
        }

        return colorRepository.save(color);
    }

    @Override
    public ColorDTO update(Integer id, ColorDTO color) {
        if (color == null || color.getNombre() == null || color.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del color no puede ser nulo o vacío para la actualización.");
        }

        ColorDTO existingColor = colorRepository.findById(id).orElse(null);
        if (existingColor == null) {
            return null;
        }
        
        try {
            return colorRepository.save(color);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Error al actualizar el color: el nombre '" + color.getNombre() + "' ya está en uso.", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        if (colorRepository.existsById(id)) {
            colorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
