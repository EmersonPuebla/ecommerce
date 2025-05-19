package cl.ecommerce.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ecommerce.ecommerce.dto.UnidadMedidaDTO;
import cl.ecommerce.ecommerce.repository.UnidadMedidaRepository;
import cl.ecommerce.ecommerce.service.IUnidadMedidaService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UnidadMedidaImpl implements IUnidadMedidaService{
    
    @Autowired
    UnidadMedidaRepository unidadMedidaRepository;

    public List<UnidadMedidaDTO> findAll(){
        return unidadMedidaRepository.findAll();
    }

     public UnidadMedidaDTO findById(Integer id) {
        return unidadMedidaRepository.findById(id).get();
    }

    public UnidadMedidaDTO save(UnidadMedidaDTO unidad_medida) {
        return unidadMedidaRepository.save(unidad_medida);
    }

    public UnidadMedidaDTO update(Integer id, UnidadMedidaDTO unidad_medida){
        if (unidadMedidaRepository.findById(id) != null){
            return unidadMedidaRepository.save(unidad_medida);
        }
        return null;
        
    }

    public void delete(Integer id) {
        unidadMedidaRepository.deleteById(id);
    }

}
