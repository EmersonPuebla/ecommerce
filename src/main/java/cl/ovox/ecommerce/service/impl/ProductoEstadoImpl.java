package cl.ovox.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import cl.ovox.ecommerce.dto.ProductoEstadoDTO;
import cl.ovox.ecommerce.repository.ProductoEstadoRepository;
import cl.ovox.ecommerce.service.IProductoEstadoService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductoEstadoImpl implements IProductoEstadoService {

    @Autowired
    private ProductoEstadoRepository productoEstadoRepository;

    @Override
    public List<ProductoEstadoDTO> findAll() {
        return productoEstadoRepository.findAll();
    }

    @Override
    public ProductoEstadoDTO findById(Integer id) {
        return productoEstadoRepository.findById(id).orElse(null);
    }

    public ProductoEstadoDTO findByNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return null;
        }
        return productoEstadoRepository.findByNombre(nombre.toLowerCase()).orElse(null);
    }

    @Override
    public ProductoEstadoDTO save(ProductoEstadoDTO productoEstado) {
        if (productoEstado == null || productoEstado.getNombre() == null || productoEstado.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El estado de producto y su nombre no pueden ser nulos o vacíos.");
        }

        String nombreNormalizado = productoEstado.getNombre().trim().toLowerCase();
        productoEstado.setNombre(nombreNormalizado);

        Optional<ProductoEstadoDTO> existingProductoEstado = productoEstadoRepository.findByNombre(nombreNormalizado);
        if (existingProductoEstado.isPresent()) {
            if (productoEstado.getId() == null || !productoEstado.getId().equals(existingProductoEstado.get().getId())) {
                throw new IllegalArgumentException("El estado con el nombre '" + productoEstado.getNombre() + "' ya existe.");
            }
        }

        try {
            return productoEstadoRepository.save(productoEstado);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Error al guardar el estado: el nombre '" + productoEstado.getNombre() + "' ya está en uso.", e);
        }
    }

    @Override
    public ProductoEstadoDTO update(Integer id, ProductoEstadoDTO productoEstado) {
        if (productoEstado == null || productoEstado.getNombre() == null || productoEstado.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El estado de producto y su nombre no pueden ser nulos o vacíos para la actualización.");
        }

        ProductoEstadoDTO existingProductoEstado = productoEstadoRepository.findById(id).orElse(null);
        if (existingProductoEstado == null) {
            return null;
        }

        String nombreNormalizado = productoEstado.getNombre().trim().toLowerCase();
        productoEstado.setNombre(nombreNormalizado);
        productoEstado.setId(id);

        Optional<ProductoEstadoDTO> existingProductoEstadoWithSameName = productoEstadoRepository.findByNombre(nombreNormalizado);
        if (existingProductoEstadoWithSameName.isPresent() && !existingProductoEstadoWithSameName.get().getId().equals(id)) {
            throw new IllegalArgumentException("El nombre '" + productoEstado.getNombre() + "' ya está en uso por otro estado.");
        }

        try {
            return productoEstadoRepository.save(productoEstado);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Error al actualizar el estado: el nombre '" + productoEstado.getNombre() + "' ya está en uso.", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        if (productoEstadoRepository.existsById(id)) {
            productoEstadoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}