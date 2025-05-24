package cl.ovox.ecommerce.service;

import java.util.List;

import cl.ovox.ecommerce.dto.ColorDTO;

public interface IColorService {
    List<ColorDTO> findAll();
    ColorDTO findById(Integer id);
    ColorDTO save(ColorDTO color);
    ColorDTO update(Integer id, ColorDTO categoria);
    void delete(Integer id);
}
