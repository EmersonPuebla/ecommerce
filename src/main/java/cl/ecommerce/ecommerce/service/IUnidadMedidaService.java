package cl.ecommerce.ecommerce.service;

import java.util.List;

import cl.ecommerce.ecommerce.dto.UnidadMedidaDTO;

public interface IUnidadMedidaService{

    List<UnidadMedidaDTO> findAll();
    UnidadMedidaDTO findById(Integer id);
    UnidadMedidaDTO save(UnidadMedidaDTO unidad_medida);
    UnidadMedidaDTO update(Integer id, UnidadMedidaDTO unidad_medida);
    void delete(Integer id);

}
