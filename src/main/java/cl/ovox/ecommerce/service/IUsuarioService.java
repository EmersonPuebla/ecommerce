package cl.ovox.ecommerce.service;

import java.util.List;

import cl.ovox.ecommerce.dto.UsuarioDTO;

public interface IUsuarioService {
    List<UsuarioDTO> findAll();
    UsuarioDTO findByRut(String rut);
    UsuarioDTO save(UsuarioDTO usuario);
    UsuarioDTO update(String rut, UsuarioDTO usuario);
    void delete(String rut);

}
