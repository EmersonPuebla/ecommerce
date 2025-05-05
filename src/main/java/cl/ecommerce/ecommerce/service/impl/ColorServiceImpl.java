package cl.ecommerce.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cl.ecommerce.ecommerce.dto.ColorDTO;
import cl.ecommerce.ecommerce.repository.ColorRepository;
import cl.ecommerce.ecommerce.service.IColorService;

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
