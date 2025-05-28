package cl.ovox.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import cl.ovox.ecommerce.dto.UnidadMedidaDTO;
import cl.ovox.ecommerce.repository.UnidadMedidaRepository;
import cl.ovox.ecommerce.service.IUnidadMedidaService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UnidadMedidaImpl implements IUnidadMedidaService{
    
    @Autowired
    UnidadMedidaRepository unidadMedidaRepository;

    @Override
    public List<UnidadMedidaDTO> findAll(){
        return unidadMedidaRepository.findAll();
    }
    @Override
    public UnidadMedidaDTO findById(Integer id) {
        return unidadMedidaRepository.findById(id).orElse(null);
    }

    public UnidadMedidaDTO findByNombre(String nombre){
        if (nombre == null || nombre.trim().isEmpty()) {
            return null;
        }
        return unidadMedidaRepository.findByNombre(nombre.toLowerCase()).orElse(null);
    }

    public UnidadMedidaDTO findBySimbolo(String simbolo){
        if (simbolo == null || simbolo.trim().isEmpty()) {
            return null;
        }
        return unidadMedidaRepository.findBySimbolo(simbolo.toLowerCase()).orElse(null);
    }
    @Override
    public UnidadMedidaDTO save(UnidadMedidaDTO unidadMedida) {

        String nombreNormalizado = unidadMedida.getNombre().toLowerCase();
        unidadMedida.setNombre(nombreNormalizado);

        Optional<UnidadMedidaDTO> existingUnidadMedida = unidadMedidaRepository.findByNombre(nombreNormalizado);

        if (existingUnidadMedida.isPresent()) {
            if (unidadMedida.getId() == null || !unidadMedida.getId().equals(existingUnidadMedida.get().getId())) {
                throw new IllegalArgumentException("La unidad de medida '" + unidadMedida.getNombre() + "' ya existe.");
            }
        }

        try{
            return unidadMedidaRepository.save(unidadMedida);
        }catch (DataIntegrityViolationException e){
            throw new IllegalArgumentException("Error al guardar la unidad de medida: el nombre '" + unidadMedida.getNombre() + "' ya está en uso.", e);
        }
    }

    @Override
    public UnidadMedidaDTO update(Integer id, UnidadMedidaDTO unidadMedida){
        if (unidadMedida == null || unidadMedida.getNombre() == null || unidadMedida.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("La unidad de medida y su nombre no pueden ser nulos o vacíos para la actualización.");
        }

        UnidadMedidaDTO existingUnidadMedida = unidadMedidaRepository.findById(id).orElse(null);
        if (existingUnidadMedida == null) {
            return null;
        }

        String nombreNormalizado = unidadMedida.getNombre().trim().toLowerCase();
        unidadMedida.setNombre(nombreNormalizado);
        unidadMedida.setId(id);

        Optional<UnidadMedidaDTO> existingUnidadMedidaWithSameName = unidadMedidaRepository.findByNombre(nombreNormalizado);
        if (existingUnidadMedidaWithSameName.isPresent() && !existingUnidadMedidaWithSameName.get().getId().equals(id)) {
            throw new IllegalArgumentException("El nombre '" + unidadMedida.getNombre() + "' ya está en uso por otra unidad de medida.");
        }

        try {
            return unidadMedidaRepository.save(unidadMedida);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Error al actualizar el estado: el nombre '" + unidadMedida.getNombre() + "' ya está en uso.", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        if (unidadMedidaRepository.existsById(id)) {
            unidadMedidaRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
