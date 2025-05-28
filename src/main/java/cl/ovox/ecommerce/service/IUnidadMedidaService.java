package cl.ovox.ecommerce.service;

import java.util.List;

import cl.ovox.ecommerce.dto.UnidadMedidaDTO;

public interface IUnidadMedidaService{

    List<UnidadMedidaDTO> findAll();
    UnidadMedidaDTO findById(Integer id);
    UnidadMedidaDTO save(UnidadMedidaDTO unidad_medida);
    UnidadMedidaDTO update(Integer id, UnidadMedidaDTO unidad_medida);
    boolean delete(Integer id);

}
