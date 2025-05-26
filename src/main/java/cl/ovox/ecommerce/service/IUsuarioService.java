package cl.ovox.ecommerce.service;

import java.util.List;

import cl.ovox.ecommerce.dto.UsuarioDTO;

public interface IUsuarioService {
    List<UsuarioDTO> findAll();
    UsuarioDTO findByRut(Integer rut);
    UsuarioDTO save(UsuarioDTO usuario);
    UsuarioDTO update(Integer rut, UsuarioDTO usuario);
    void delete(Integer rut);

}
